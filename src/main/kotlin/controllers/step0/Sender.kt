package org.example.controllers.step0

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class Sender(

    @Value("\${rabbitmq.queue.name}")
    val queueName: String,

    private val rabbitTemplate: RabbitTemplate
) {
    fun send(message: String) {
        rabbitTemplate.convertAndSend(queueName, message)
        println("[#] send: $message")
    }


}