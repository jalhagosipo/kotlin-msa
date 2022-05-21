package com.microservices.chapter4

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class CustomerHandler(
    val customerService: CustomerService
) {
    fun get(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ok().body(
            customerService.getCustomer(serverRequest.pathVariable("id").toInt())
                .flatMap { ok().body(fromValue(it)) }
                .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

        )
    }

    fun search(serverRequest: ServerRequest) =
        ok().body(customerService.searchCustomers(serverRequest.queryParam("nameFilter").orElse("")), Customer::class.java)
}