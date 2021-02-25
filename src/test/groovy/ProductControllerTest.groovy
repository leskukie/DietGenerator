import com.fasterxml.jackson.databind.ObjectMapper
import com.leskukie.dietgenerator.controller.ProductController
import com.leskukie.dietgenerator.model.Product
import com.leskukie.dietgenerator.model.ProductType
import com.leskukie.dietgenerator.repository.ProductRepository
import org.apache.commons.lang.RandomStringUtils
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification


class ProductControllerTest extends Specification {
    def productRepository = Mock(ProductRepository)
    def productController = new ProductController(productRepository)
    ObjectMapper objectMapper = new ObjectMapper()
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build()

    def "Should get 404 when product does not exists"() {
        given:
        productRepository.findById(1) >> Optional.empty()
        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get('/products/1')).andReturn().response
        then:
        response.status == HttpStatus.NOT_FOUND.value()
    }

    def "Should get correct product"() {
        given:
        Product product = generateProduct()
        productRepository.findById(product.getId()) >> Optional.of(product)
        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/products/" + product.getId())).andReturn().response
        then:
        response.status == HttpStatus.OK.value()
        and:
        with (objectMapper.readValue(response.contentAsString, Map)) {
            new Float(it.get("energy")) == product.getEnergy()
        }
    }
    def "Should get all products when getAllProducts requested"() {
        given:
        List<Product> allProducts = new ArrayList<>();
        allProducts.add(generateProduct())
        allProducts.add(generateProduct())
        productRepository.findAll() >> allProducts
        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get('/products')).andReturn().response
        then:
        response.status == HttpStatus.OK.value()
    }

    def "Should create correct product"() {
        given:
        Product product = generateProduct()
        productRepository.save(product) >> product
        when:
        def response = mockMvc.perform(
                    MockMvcRequestBuilders.post("/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(product)))
                .andReturn().response
        then:
        response.status == HttpStatus.CREATED.value()
    }

    def "Should delete all products"() {
        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders.delete("/products")).andReturn().response
        then:
        response.status == HttpStatus.OK.value()
        and:
        1 * productRepository.deleteAll()
    }

    def "Should delete certain product by id"() {
        given:
        Product product = generateProduct()
        productRepository.findById(product.getId()) >> Optional.of(product)
        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders.delete("/products/" + product.getId())).andReturn().response
        then:
        response.status == HttpStatus.OK.value()
        and:
        1 * productRepository.deleteById(product.getId())
    }

    def "Should get 404 when deleting not existing product"() {
        given:
        productRepository.findById(1) >> Optional.empty()
        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders.delete("/products/1")).andReturn().response
        then:
        response.status == HttpStatus.NOT_FOUND.value()
    }

    private Product generateProduct() {
        List<ProductType> productTypes = new ArrayList<>();
        productTypes.add(ProductType.HEALTHY)
        return new Product.ProductBuilder()
                .id(new Random().nextLong())
                .name(RandomStringUtils.random(10))
                .energy(new Random().nextFloat())
                .proteins(new Random().nextFloat())
                .fats(new Random().nextFloat())
                .carbs(new Random().nextFloat())
                .types(productTypes)
                .build()
    }
}