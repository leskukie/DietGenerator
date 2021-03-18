import com.leskukie.dietgenerator.controller.IngredientController
import com.leskukie.dietgenerator.controller.ProductController
import com.leskukie.dietgenerator.model.Product
import com.leskukie.dietgenerator.model.ProductType
import com.leskukie.dietgenerator.repository.ProductRepository
import org.apache.commons.lang.RandomStringUtils

class ProductControllerTest extends CrudControllerTest<Product, ProductRepository, ProductController> {

    def createSut() {
        def repo = Mock(ProductRepository)
        ["/products", repo, new ProductController(repo)]
    }

    def generateRecord() {
        List<ProductType> productTypes = new ArrayList<>();
        productTypes.add(ProductType.HEALTHY)
        return new Product.ProductBuilder()
                .id(1)
                .name(RandomStringUtils.random(10))
                .energy(new Random().nextFloat())
                .proteins(new Random().nextFloat())
                .fats(new Random().nextFloat())
                .carbs(new Random().nextFloat())
                .types(productTypes)
                .build()
    }
}