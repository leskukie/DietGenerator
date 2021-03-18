import com.leskukie.dietgenerator.controller.IngredientController
import com.leskukie.dietgenerator.model.Ingredient
import com.leskukie.dietgenerator.repository.IngredientRepository

class IngredientControllerTest extends CrudControllerTest<Ingredient, IngredientRepository, IngredientController> {

    def createSut() {
        def repo = Mock(IngredientRepository)
        ["/ingredients", repo, new IngredientController(repo)]
    }

    def generateRecord() {
        return new Ingredient().setId(1).setWeight(100)
    }
}