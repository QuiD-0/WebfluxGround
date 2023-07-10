package com.quid.webfluxground.notification.gateway.event.producer

import com.quid.webfluxground.notification.domain.Notification
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

interface PushProducer {

    fun publish(notification: Notification)

    @Component
    class KafkaPushProducer(
        private val kafkaTemplate: KafkaTemplate<String, Notification>
    ) : PushProducer {

        override fun publish(notification: Notification): Unit =
            Unit.let { kafkaTemplate.send("push", notification) }
    }
}