package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RabbitMQStuderApplication

fun main(args: Array<String>) {
    runApplication<RabbitMQStuderApplication>(*args)
}