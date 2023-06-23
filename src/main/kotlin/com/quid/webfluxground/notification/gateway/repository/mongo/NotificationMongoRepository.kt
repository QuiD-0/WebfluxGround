package com.quid.webfluxground.notification.gateway.repository.mongo

import com.quid.webfluxground.notification.gateway.repository.NotificationDocument
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface NotificationMongoRepository : ReactiveMongoRepository<NotificationDocument, ObjectId> {
    fun findBySender(sender: String): Flux<NotificationDocument>
    fun findByReceiver(receiver: String): Flux<NotificationDocument>
}