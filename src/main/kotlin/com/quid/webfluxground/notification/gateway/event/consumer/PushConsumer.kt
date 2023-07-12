package com.quid.webfluxground.notification.gateway.event.consumer

import com.quid.webfluxground.notification.domain.Notification
import com.quid.webfluxground.notification.handler.NotificationHandler
import com.quid.webfluxground.notification.usecase.SaveNotification
import com.quid.webfluxground.notification.usecase.SendNotification
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

interface PushConsumer {

    fun consume(notification: Notification, ack: Acknowledgment)

    @Component
    class KafkaPushConsumer(
        private val sendNotification: SendNotification,
        private val saveNotification: SaveNotification
    ) : PushConsumer {

        @KafkaListener(topics = ["push"], groupId = "push")
        override fun consume(
            notification: Notification,
            ack: Acknowledgment
        ) {
            saveNotification.execute(notification)
                .map { sendNotification.execute(it) }
                .doOnSuccess { ack.acknowledge() }
                .subscribe()
        }
    }
}