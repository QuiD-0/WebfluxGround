package com.quid.webfluxground.notification.gateway.repository

import com.quid.webfluxground.notification.domain.Notification
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "notification")
class NotificationDocument(
    val id: ObjectId,
    val title: String,
    val message: String,
    val sender: String,
    @Indexed
    val receiver: String,
    val timestamp: LocalDateTime
) {
    fun toDomain() : Notification{
        return Notification(
            id = id.toHexString(),
            title = title,
            message = message,
            sender = sender,
            receiver = receiver,
            timestamp = timestamp
        )
    }

    companion object{
        fun document(notification: Notification) = NotificationDocument(
            id = ObjectId(notification.id),
            title = notification.title,
            message = notification.message,
            sender = notification.sender,
            receiver = notification.receiver,
            timestamp = notification.timestamp
        )
    }
}