package br.com.programadorcriativo.billmanager.rest

import br.com.programadorcriativo.billmanager.entity.DomesticBill
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
import org.springframework.test.web.servlet.get
import java.math.BigDecimal
import java.time.LocalDate
import kotlin.random.Random

/**
 * Anotação do springboot que informa que os testes sero executados no contexto da aplicaçao
 */
@SpringBootTest
/**
 * Anotação que permite utilizar uma instancia já pré-configurada do springboot tests para facilitar os testes integrados
 */
@AutoConfigureMockMvc
/**
 * Anotação que coloca o profile test para pegar as configurações de tests do arquivo de testes application-test.properties
 */
@ActiveProfiles("test")
/**
 * Essa anotação é uma otmização que utiliza apenas uma instancia da classe de testes para todos os testes dessa classe.
 * Sem essa anotação para cada teste executado a classe seria instanciada novamente. No nosso caso, essa nova
 * instanciação não é necessária
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DomesticBillResourceIntTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val mapper: ObjectMapper
) {

    @Test
    fun contextLoads() {
    }

    @Test
    fun `post successfully DomesticBill`() {
        val postPath = "/api/domestic-bills"
        val domesticBill = buildDomesticBill()
        mockMvc.post(postPath) {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(domesticBill)
        }.andExpect {
            status { isCreated() }
        }//.andDo { print() }
    }

    @Test
    fun `get by id then success`() {
        val getPath = "/api/domestic-bills"
        mockMvc.get("$getPath/1")
            .andExpect {
                status { isOk() }
                jsonPath("$.id") { value(1) }
            }//.andDo { print() }
    }

    @Test
    fun `patch successfully DomesticBill`() {
        val putPath = "/api/domestic-bills"
        val domesticBill = buildDomesticBill()
        mockMvc.put(putPath) {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(domesticBill)
        }.andExpect {
            status { isNoContent() }
        }//.andDo { print() }
    }

    @Test
    fun `delete successfully DomesticBill`() {
        val deletePath = "/api/domestic-bills"
        mockMvc.delete("$deletePath/1")
            .andExpect {
                status { isNoContent() }
            }//.andDo { print() }
    }

    private fun buildDomesticBill(
        id: Long = Random.nextLong(),
        description: String = "A description ${Random.nextLong()}",
        dueDate: LocalDate = LocalDate.now(),
        billAmount: BigDecimal = BigDecimal("10.0"),
        owner: Person = Person(
            id = Random.nextLong(),
            name = "Name ${Random.nextLong()}",
            cpf = "12345678910"
        )
    ) = DomesticBill(
        id,
        description,
        dueDate,
        billAmount,
        owner = owner
    )
}