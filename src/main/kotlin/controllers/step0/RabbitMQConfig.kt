package org.example.controllers.step0

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig(

    private val rabbitMQProperties: RabbitMQProperties,

    @Value("\${rabbitmq.queue.name}")
    val queueName: String,

    @Value("\${rabbitmq.exchange.name}")
    val exchangeName: String,

    @Value("\${rabbitmq.routing.key}")
    val routingKey: String,

) {

    @Bean
    fun queue(): Queue {
        return Queue(queueName, false)
    }

//    @Bean
//    fun directExchange(): DirectExchange {
//        return DirectExchange(exchangeName)
//    }
//
//    @Bean
//    fun binding(queue: Queue, exchange: DirectExchange): Binding {
//        return BindingBuilder.bind(queue).to(exchange).with(routingKey)
//    }
//
//    @Bean
//    fun connectionFactory(): CachingConnectionFactory {
//        val connectionFactory = CachingConnectionFactory()
//        connectionFactory.host = rabbitMQProperties.host
//        connectionFactory.port = rabbitMQProperties.port
//        connectionFactory.username = rabbitMQProperties.username
//        connectionFactory.setPassword(rabbitMQProperties.password)
//        return connectionFactory
//    }

//    @Bean
//    fun jackson2JsonMessageConverter(): MessageConverter {
//        return Jackson2JsonMessageConverter()
//    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
            //rabbitTemplate.messageConverter = jackson2JsonMessageConverter()
        return rabbitTemplate
    }

    @Bean
    fun container(connectionFactory: ConnectionFactory, listenerAdapter: MessageListenerAdapter): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(queueName)
        container.messageListener = listenerAdapter
        return container
    }

    @Bean
    fun listenerAdapter(receiver: Receiver): MessageListenerAdapter {
        return MessageListenerAdapter(receiver, "handleMessage")
    }

}