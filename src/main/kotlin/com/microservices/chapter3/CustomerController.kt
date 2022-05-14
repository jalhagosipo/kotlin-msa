package com.microservices.chapter3

import org.springframework.web.bind.annotation.*

@RestController
class CustomerController {
    @RequestMapping(value = ["/customer"], method = [RequestMethod.GET])
    fun getCustomer() = Customer(1, "Kotlin")
}