package com.microservices.chapter4

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router

@Component
class CusotomerRouter(
    private val customerHandler: CustomerHandler
) {
    @Bean
    fun customerRoutes(): RouterFunction<*> = router {
        "/functional".nest {
            "/customer".nest {
                GET("/{id}", customerHandler::get)
                POST("/", customerHandler::get)
            }
            "/customers".nest {
                GET("/", customerHandler::get)
            }
        }
    }
}