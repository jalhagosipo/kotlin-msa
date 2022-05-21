package com.microservices.chapter4

import com.microservices.chapter4.Customer.Telephone
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
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

    override fun getCustomer(id: Int) = customers[id]?.toMono() ?: Mono.empty()


    override fun searchCustomers(nameFilter: String) =
        customers
            .filter {
                it.value.name.contains(nameFilter, true)
            }.map{ it.value }.toFlux()

    override fun createCustomer(customerMono: Mono<Customer>): Mono<Customer> {
        return customerMono.map {
            customers[it.id] = it
            it
        }
    }
}