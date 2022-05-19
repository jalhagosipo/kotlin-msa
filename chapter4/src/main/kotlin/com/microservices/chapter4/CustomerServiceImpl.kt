package com.microservices.chapter4

import com.microservices.chapter4.Customer.Telephone
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {

    companion object {
        val initialCustomers = arrayOf(
            Customer(1, "Kotlin"),
            Customer(2, "Spring"),
            Customer(3, "Microservice", Telephone("+44", "7123456789"))
        )
    }
    val customers = ConcurrentHashMap(initialCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int) = customers[id]


    override fun searchCustomers(nameFilter: String): List<Customer> {
        return customers
            .filter {
                it.value.name.contains(nameFilter, true)
            }.map{ it.value }
    }
}