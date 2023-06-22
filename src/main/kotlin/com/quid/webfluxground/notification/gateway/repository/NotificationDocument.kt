package com.quid.webfluxground.notification.gateway.repository

import com.quid.webfluxground.notification.domain.Notification
import org.bson.types.ObjectId
import java.time.LocalDateTime

class NotificationDocument(
    val id: ObjectId,
    val message: String,
    val sender: String,
    val receiver: String,
    val timestamp: LocalDateTime
) {
    fun toDomain() : Notification{
        return Notification(
            id = id.toHexString(),
            message = message,
            sender = sender,
            receiver = receiver,
            timestamp = timestamp
        )
    }

    companion object{
        fun document(notification: Notification) = NotificationDocument(
            id = ObjectId(notification.id),
            message = notification.message,
            sender = notification.sender,
            receiver = notification.receiver,
            timestamp = notification.timestamp
        )
    }
}