package br.com.programadorcriativo.billmanager.rest

import br.com.programadorcriativo.billmanager.entity.DomesticBill
import br.com.programadorcriativo.billmanager.entity.Person
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.net.URI
import java.time.LocalDate
import kotlin.random.Random

/**
 * Que informa ao springboot que esse é uma classe que possui API Rest, também chamado de EndPoint
 */
@RestController
/**
 * Anotação que informa ao springboot o caminho que da URL que essa classe vai responder (Ex: https://localhost:8080/api
 */
@RequestMapping("/api", produces = [MediaType.APPLICATION_JSON_VALUE])
class DomesticBillResource {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/domestic-bills")
    fun create(@RequestBody domesticBill: DomesticBill): ResponseEntity<Void> {
        log.debug("Request to create a DomesticBill: {}", domesticBill)
        return ResponseEntity.created(URI.create("/1")).build()
    }

    @GetMapping("/domestic-bills/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<DomesticBill> {
        log.debug("Request to find DomesticBill by id: {}", id)
        return ResponseEntity.ok(mockReturn())
    }

    @PutMapping("/domestic-bills/{id}")
    fun update(@PathVariable id: Long, @RequestBody domesticBill: DomesticBill): ResponseEntity<Void> {
        log.debug("Request to update a DomesticBill: {}", domesticBill)
        domesticBill.id = id
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/domestic-bills/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> {
        log.debug("Request to delete DomesticBill by id: {}", id)
        return ResponseEntity.noContent().build()
    }

    private fun mockReturn(
        id: Long = 1,
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