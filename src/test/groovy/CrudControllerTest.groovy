import com.fasterxml.jackson.databind.ObjectMapper
import com.leskukie.dietgenerator.controller.CrudController
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

abstract class CrudControllerTest<T, R extends JpaRepository<T, Long>, C extends CrudController<T, R>> extends Specification {
    private String endpoint
    private R repository
    private C controller
    private MockMvc mockMvc
    ObjectMapper objectMapper

    def setup() {
        (endpoint, repository, controller) = createSut()
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
        objectMapper = new ObjectMapper()
    }

    abstract createSut()
    abstract generateRecord()

    def "Should get 404 when record does not exists"() {
        given:
        repository.findById(1) >> Optional.empty()
        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get(endpoint + '/1')).andReturn().response
        then:
        response.status == HttpStatus.NOT_FOUND.value()
    }

    def "Should get correct record"() {
        given:
        T record = generateRecord()
        repository.findById(record.getId()) >> Optional.of(record)
        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get(endpoint + "/" + record.getId())).andReturn().response
        then:
        response.status == HttpStatus.OK.value()
        and:
        with (objectMapper.readValue(response.contentAsString, Map)) {
            new Float(it.get("id")) == record.getId()
        }
    }

    def "Should get all records when getAll requested"() {
        given:
        List<T> allRecords = new ArrayList<>();
        allRecords.add(generateRecord())
        allRecords.add(generateRecord())
        repository.findAll() >> allRecords
        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get(endpoint)).andReturn().response
        then:
        response.status == HttpStatus.OK.value()
    }

    def "Should create correct record"() {
        given:
        T record = generateRecord()
        repository.save(record) >> record
        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders.post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(record)))
                .andReturn().response
        then:
        response.status == HttpStatus.CREATED.value()
    }

    def "Should delete all records"() {
        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders.delete(endpoint)).andReturn().response
        then:
        response.status == HttpStatus.OK.value()
        and:
        1 * repository.deleteAll()
    }

    def "Should delete certain record by id"() {
        given:
        T record = generateRecord()
        repository.findById(record.getId()) >> Optional.of(record)
        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders.delete(endpoint + "/" + record.getId())).andReturn().response
        then:
        response.status == HttpStatus.OK.value()
        and:
        1 * repository.deleteById(record.getId())
    }

    def "Should get 404 when deleting not existing record"() {
        given:
        repository.findById(1) >> Optional.empty()
        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders.delete(endpoint + "/1")).andReturn().response
        then:
        response.status == HttpStatus.NOT_FOUND.value()
    }
}