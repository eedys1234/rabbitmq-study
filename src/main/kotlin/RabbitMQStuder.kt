package org.example

import org.example.controllers.step0.RabbitMQProperties
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(RabbitMQProperties::class)
class RabbitMQStuderApplication

fun main(args: Array<String>) {
    runApplication<RabbitMQStuderApplication>(*args)
}