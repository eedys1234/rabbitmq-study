package org.example.controllers.step0

import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.stereotype.Component

@Component
class Receiver {
    fun handleMessage(message: String) {
        println("[#] Received: $message")
    }
}