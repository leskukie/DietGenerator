import com.leskukie.DietGenerator.model.Product
import com.leskukie.DietGenerator.model.ProductType
import com.leskukie.DietGenerator.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import spock.lang.Specification

@DataJpaTest
class DataJpaTestIntegrationSpec extends Specification {

    @Autowired
    TestEntityManager entityManager

    @Autowired
    ProductRepository productRepository

    def "spring context loads for data jpa"() {
        given: "one existing product"
        entityManager.persist(Product.builder()
                .id(1)
                .name("Banan")
                .energy(90.0)
                .proteins(1.1)
                .fats(0.3)
                .carbs(20.2)
                .types(List.of(ProductType.HEALTHY)))

        expect: "the correct count is inside the repository"
        productRepository.count() == 1L
    }
}