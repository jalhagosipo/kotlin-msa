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

    override fun createCustomer(customerMono: Mono<Customer>): Mono<*> {
        return customerMono.map {
            customers[it.id] = it
            // 이렇게 그냥 두면 빈객체{}를 리턴. 수신된 모노엣 매퍼(mapper)를 사용해 변환하기 때문. 추가 객체를 생성하지 않았으므로 빈 객체가 반환됨
            // it // 일반 post 한것과 같은 동일한 객체 리턴
            // Mono.empty<Any>() // 명시적 빈객체
        }
    }
}