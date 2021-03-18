import com.leskukie.dietgenerator.controller.DietController
import com.leskukie.dietgenerator.model.Diet
import com.leskukie.dietgenerator.repository.DietRepository

class DietControllerTest extends CrudControllerTest<Diet, DietRepository, DietController> {

    def createSut() {
        def repo = Mock(DietRepository)
        ["/diets", repo, new DietController(repo)]
    }

    def generateRecord() {
        return new Diet().setId(1)
    }
}
