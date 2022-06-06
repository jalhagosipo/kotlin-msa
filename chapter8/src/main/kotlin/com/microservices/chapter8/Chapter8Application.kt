package com.microservices.chapter8

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class Chapter8Application

fun main(args: Array<String>) {
	runApplication<Chapter8Application>(*args)
}
