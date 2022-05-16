package com.microservices.chapter2

import org.springframework.beans.factory.annotation.Value

class SpelExample {

    // =7
    @Value(value = "#{4+3}")
    private lateinit var result1: Number

    // one.value 나누기 another.value
    @Value(value = "#{ \${one.value} div \${another.value} }")
    private lateinit var result2: Number

    // one, another 같아야함
    @Value(value = "#{ \${one.value} eq \${another.value} }")
    private lateinit var result3: Comparable<Boolean>

    @Value(value = "#{ \${one.value} and \${another.value} }")
    private lateinit var result4: Comparable<Boolean>

    // 없으면 hello로 설정됨
    @Value(value = "\${service.message.simple:hello}")
    private lateinit var result5: String

    // some.value가 영어 또는 숫자면 true로 설정
    @Value(value = "#{ '\${some.value}' matches '[a-zA-Z\\s]+'}")
    private lateinit var result6: Comparable<Boolean>
}