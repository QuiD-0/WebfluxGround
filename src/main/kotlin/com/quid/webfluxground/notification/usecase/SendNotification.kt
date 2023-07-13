package com.quid.webfluxground.notification.usecase

import com.quid.webfluxground.notification.domain.Notification
import com.quid.webfluxground.notification.gateway.repository.NotificationRepository
import com.quid.webfluxground.notification.handler.NotificationHandler
import org.springframework.stereotype.Service

interface SendNotification {

    fun execute(notification: Notification)

    @Service
    class SendNotificationUseCase(
        private val notificationHandler: NotificationHandler,
        private val notificationRepository: NotificationRepository
    ) : SendNotification {

        override fun execute(notification: Notification) {
            notificationRepository.save(notification)
                .map { notificationHandler.publish(it) }
                .subscribe()
        }
    }
}