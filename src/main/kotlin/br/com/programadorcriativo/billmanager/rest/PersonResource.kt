package br.com.programadorcriativo.billmanager.rest

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
class PersonResource {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/persons")
    fun create(@RequestBody person: Person): ResponseEntity<Void> {
        log.debug("Request to create a Person: {}", person)
        return ResponseEntity.created(URI.create("/1")).build()
    }

    @GetMapping("/persons/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Person> {
        log.debug("Request to find Person by id: {}", id)
        return ResponseEntity.ok(mockReturn())
    }

    @PutMapping("/persons/{id}")
    fun update(@PathVariable id: Long, @RequestBody person: Person): ResponseEntity<Void> {
        log.debug("Request to update a Person: {}", person)
        person.id = id
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/persons/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> {
        log.debug("Request to delete Person by id: {}", id)
        return ResponseEntity.noContent().build()
    }

    private fun mockReturn(
            id: Long = 1,
            name: String = "Name ${Random.nextLong()}",
            cpf:String = "12345678910"
    ) = Person(id, name, cpf )
}