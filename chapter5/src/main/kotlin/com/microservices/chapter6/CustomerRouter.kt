package com.microservices.chapter6

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class CustomerRouter(
    private val customerHanlder: CustomerHandler
) {
    @Bean
    fun customerRoutes() = router {
        "/customer".nest {
            GET("/{id}", customerHanlder::get)
            POST("/", customerHanlder::create)
            DELETE("/{id}", customerHanlder::delete)
        }
        "/customers".nest {
            GET("/", customerHanlder::search)
        }
    }
}