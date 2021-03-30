package br.com.programadorcriativo.billmanager.rest

import br.com.programadorcriativo.billmanager.entity.Person
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.*
import kotlin.random.Random

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonResourceIntTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val mapper: ObjectMapper
) {

    @Test
    fun contextLoads() {
    }

    @Test
    fun `post successfully Person`() {
        val postPath = "/api/persons"
        val person = buildPerson()
        mockMvc.post(postPath) {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(person)
        }.andExpect {
            status { isCreated() }
        }//.andDo { print() }
    }

    @Test
    fun `get by id then success`() {
        val getPath = "/api/persons"
        mockMvc.get("$getPath/1")
            .andExpect {
                status { isOk() }
                jsonPath("$.id") { value(1) }
            }//.andDo { print() }
    }

    @Test
    fun `put successfully Person`() {
        val putPath = "/api/persons"
        val person = buildPerson()
        mockMvc.put("$putPath/1") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(person)
        }.andExpect {
            status { isNoContent() }
        }//.andDo { print() }
    }

    @Test
    fun `delete successfully Person`() {
        val deletePath = "/api/persons"
        mockMvc.delete("$deletePath/1")
            .andExpect {
                status { isNoContent() }
            }//.andDo { print() }
    }

    private fun buildPerson(
        id: Long = Random.nextLong(),
        name: String = "Name ${Random.nextLong()}",
        cpf:String = "12345678910"
    ) = Person(id, name, cpf )
}