package com.microservices.chapter2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
class Chapter2Application {
	// 빈으로 노출되지 않도록 concrete class가 아닌 인터페이스를 반환하는 함수를 정의
	@Bean
	fun exampleService(): ServiceInterface = ExampleService()
}


@Controller
//class FirstController(val exampleService: ExampleService) {
class FirstController() {

	@Autowired
	lateinit var service: ServiceInterface

	@RequestMapping(value = ["/user/{name}"], method = [RequestMethod.GET])
	@ResponseBody
	fun hello(@PathVariable name: String) = service.getHello(name)
}

fun main(args: Array<String>) {
	runApplication<Chapter2Application>(*args)
}
