package com.microservices.chapter9

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class CustomerServiceImpl : CustomerService {
    companion object {
        private val initializerCustomer = arrayOf(
                Customer(1, "Kotlin"),
                Customer(2, "Spring"),
                Customer(3, "Microservice")
        )
        private val customers = ConcurrentHashMap(initializerCustomer.associateBy(Customer::id))
    }

    override fun getCustomer(id: Int) = customers[id]
    override fun getAllCustomers() = customers.map(Map.Entry<Int, Customer>::value).toList()
}