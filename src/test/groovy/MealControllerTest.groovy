import com.leskukie.dietgenerator.controller.MealController
import com.leskukie.dietgenerator.model.Meal
import com.leskukie.dietgenerator.repository.MealRepository

class MealControllerTest extends CrudControllerTest<Meal, MealRepository, MealController> {

    def createSut() {
        def repo = Mock(MealRepository)
        ["/meals", repo, new MealController(repo)]
    }

    def generateRecord() {
        return new Meal().setId(1)
    }
}
