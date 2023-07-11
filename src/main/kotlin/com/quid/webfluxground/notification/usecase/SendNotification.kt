package com.quid.webfluxground.notification.usecase

import com.quid.webfluxground.notification.domain.Notification
import com.quid.webfluxground.notification.handler.NotificationHandler
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Service

interface SendNotification {

    fun execute(notification: Notification, ack: Acknowledgment)

    @Service
    class SendNotificationUseCase(
        private val notificationHandler: NotificationHandler,
        private val saveNotification: SaveNotification
    ) : SendNotification {

        override fun execute(notification: Notification, ack: Acknowledgment) {
            saveNotification.execute(notification)
                .doOnSuccess { notificationHandler.publish(it) }
                .doOnSuccess { ack.acknowledge() }
                .subscribe()
        }
    }
}