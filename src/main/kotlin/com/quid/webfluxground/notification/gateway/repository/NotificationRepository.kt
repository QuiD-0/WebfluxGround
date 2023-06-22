package com.quid.webfluxground.notification.gateway.repository

import com.quid.webfluxground.notification.domain.Notification
import com.quid.webfluxground.notification.gateway.repository.mongo.NotificationMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

interface NotificationRepository {
    fun save(notification: Notification): Mono<Notification>

    @Repository
    class MongoNotificationRepository(
        private val repository: NotificationMongoRepository
    ) : NotificationRepository {
        override fun save(notification: Notification): Mono<Notification> =
            NotificationDocument.document(notification)
                .let { repository.save(it) }
                .map { it.toDomain() }
    }
}