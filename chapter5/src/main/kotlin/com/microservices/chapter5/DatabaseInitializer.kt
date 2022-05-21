package com.microservices.chapter5

import com.microservices.chapter5.Customer.Telephone
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DatabaseInitializer {
    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var mongoOperations: ReactiveMongoOperations

    companion object {
        val initialCustomers = listOf(
            Customer(1, "Kotlin"),
            Customer(2, "Spring"),
            Customer(3, "Microservice", Telephone("+44", "7123456789"))
        )
    }

    @PostConstruct
    fun initData() {
        mongoOperations.collectionExists("Customers").subscribe {
            if (it != true) {
                mongoOperations.createCollection("Customers").subscribe {
                    println("Customers collections created")
                }
            } else {
                println("Customers collections already exist")
                customerRepository.saveAll(initialCustomers)
                    .subscribe {
                        println("Default customers created")
                    }
            }
        }
    }
}