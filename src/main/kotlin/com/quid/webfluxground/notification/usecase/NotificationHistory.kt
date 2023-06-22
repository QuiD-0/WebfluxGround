package com.quid.webfluxground.notification.usecase

import com.quid.webfluxground.notification.domain.Notification
import org.springframework.stereotype.Service

interface NotificationHistory {

    fun save(notification: Notification): Notification

    @Service
    class MongoNotificationHistory() : NotificationHistory {

        override fun save(notification: Notification): Notification {
            return notification
        }
    }
}