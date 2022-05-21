package com.microservices.chapter5

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class CustomerServiceImpl : CustomerService {
    @Autowired
    lateinit var customerRepository: CustomerRepository

    override fun getCustomer(id: Int): Mono<Customer> {
        return customerRepository.findById(id)
    }

    override fun createCustomer(customer: Mono<Customer>): Mono<Customer> {
        return customerRepository.create(customer)
    }
}