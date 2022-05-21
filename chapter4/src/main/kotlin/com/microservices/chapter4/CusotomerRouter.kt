package com.microservices.chapter4

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router
import reactor.kotlin.core.publisher.toMono

@Component
class CusotomerRouter {
    @Bean
    fun customerRoutes(): RouterFunction<*> = router {
        "/functional".nest {
            "/customer".nest {
                GET("/") {
                    ok().body(Customer(1, "functional web").toMono(), Customer::class.java)
                }
            }
        }
    }
}