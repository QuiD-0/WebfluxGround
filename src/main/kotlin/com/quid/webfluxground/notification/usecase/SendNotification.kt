package com.quid.webfluxground.notification.usecase

import com.quid.webfluxground.notification.domain.Notification
import com.quid.webfluxground.notification.handler.NotificationHandler
import org.springframework.stereotype.Service

interface SendNotification {

    fun execute(notification: Notification)

    @Service
    class SendNotificationUseCase(
        private val notificationHandler: NotificationHandler,
    ) : SendNotification {

        override fun execute(notification: Notification) =
            notificationHandler.publish(notification)
    }
}