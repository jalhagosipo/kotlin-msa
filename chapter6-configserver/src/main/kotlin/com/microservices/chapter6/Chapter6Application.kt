package com.microservices.chapter6

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class Chapter6Application

fun main(args: Array<String>) {
	runApplication<Chapter6Application>(*args)
}
