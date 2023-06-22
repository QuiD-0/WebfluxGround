package com.quid.webfluxground.notification.gateway.repository.mongo

import com.quid.webfluxground.notification.gateway.repository.NotificationDocument
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface NotificationReactiveMongoRepository : ReactiveMongoRepository<NotificationDocument, ObjectId> {
}