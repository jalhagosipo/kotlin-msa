package com.microservices.chapter3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {
    @Autowired
    private lateinit var customerService: CustomerService

    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int) = customerService.getCustomer(id)

    @GetMapping("/customers")
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) {
        customerService.searchCustomers(nameFilter)
    }

    @PostMapping("/customer")
    fun createCustomer(@RequestBody customer: Customer) {
        customerService.createCustomer(customer)
    }

    @DeleteMapping("/customer/{id}")
    fun deleteCustomer(@PathVariable id: Int) {
        customerService.deleteCustomer(id)
    }

    @PutMapping("/customer/{id}")
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer) {
        customerService.updateCustomer(id, customer)
    }
}