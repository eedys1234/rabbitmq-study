package org.example.controllers.step2

import org.springframework.amqp.core.AcknowledgeMode
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig(
    @Value("\${rabbitmq.queue.name}")
    val queueName: String,
) {


    @Bean
    fun queue(): Queue {
        return Queue(queueName)
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        return RabbitTemplate(connectionFactory)
    }

    @Bean
    fun container(connectionFactory: ConnectionFactory, listenerAdapter: MessageListenerAdapter): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(queueName)
        container.acknowledgeMode = AcknowledgeMode.AUTO
        container.messageListener = listenerAdapter
        return container
    }

    @Bean
    fun listenerAdapter(receiver: WorkQueueConsumer): MessageListenerAdapter {
        return MessageListenerAdapter(receiver, "workQueueTask")
    }
}