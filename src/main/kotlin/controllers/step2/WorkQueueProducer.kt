package org.example.controllers.step2

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class WorkQueueProducer(
    private val rabbitTemplate: RabbitTemplate,

    @Value("\${rabbitmq.queue.name}")
    val queueName: String
) {

    fun sendWorkQueue(message: String, duration: Long) {
        val sendMessage = "$message|$duration"
        this.rabbitTemplate.convertAndSend(queueName, sendMessage)
        println("[#] send to WorkQueue $sendMessage")
    }
}