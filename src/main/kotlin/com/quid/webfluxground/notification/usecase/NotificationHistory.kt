package com.quid.webfluxground.notification.usecase

import com.quid.webfluxground.notification.domain.Notification
import com.quid.webfluxground.notification.gateway.repository.NotificationRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

interface NotificationHistory {

    fun save(notification: Notification): Mono<Notification>

    @Service
    class MongoNotificationHistory(
        private val repository: NotificationRepository
    ) : NotificationHistory {

        override fun save(notification: Notification): Mono<Notification> =
            repository.save(notification)
    }
}