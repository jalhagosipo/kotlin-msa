package com.microservices.chapter3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {
    @Autowired
    lateinit var customers: ConcurrentHashMap<Int, Customer>

    @RequestMapping(value = ["/customer/{id}"], method = [RequestMethod.GET])
    fun getCustomer(@PathVariable id: Int) = customers[id]

    @RequestMapping(value = ["/customers"], method = [RequestMethod.GET])
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) = customers
        .filter { it.value.name.contains(nameFilter, true) }
        .map{ it.value }

    @RequestMapping(value = ["/customer"], method = [RequestMethod.POST])
    fun createCustomer(@RequestBody customer: Customer) {
        customers[customer.id] = customer
    }

    @RequestMapping(value = ["/customer/{id}"], method = [RequestMethod.DELETE])
    fun deleteCustomer(@PathVariable id: Int) = customers.remove(id)

    @RequestMapping(value = ["/customer/{id}"], method = [RequestMethod.PUT])
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer) {
        customers.remove(id)
        customers[customer.id] = customer
    }
}