package com.quid.webfluxground.notification.usecase

import com.quid.webfluxground.notification.domain.Notification
import com.quid.webfluxground.notification.gateway.repository.NotificationRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

interface SaveNotification {

    fun execute(notification: Notification): Mono<Notification>

    @Service
    class MongoNotificationHistory(
        private val repository: NotificationRepository
    ) : SaveNotification {

        override fun execute(notification: Notification): Mono<Notification> =
            repository.save(notification)
    }
}