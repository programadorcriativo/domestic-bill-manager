package br.com.programadorcriativo.billmanager.entity

import java.math.BigDecimal
import java.time.LocalDate

class Person(
    var id: Long,
    var name: String,
    var cpf: String
)

class DomesticBill(
    var id: Long,
    var description: String,
    var dueDate: LocalDate,
    var billAmount: BigDecimal,
    var owner: Person
)