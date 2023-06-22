package com.quid.webfluxground.notification.domain

import org.bson.types.ObjectId
import java.time.LocalDateTime

class Notification(
    val id: String = ObjectId().toHexString(),
    val message: String,
    val sender: String,
    val receiver: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun create(
            message: String,
            sender: String,
            receiver: String
        ): Notification = Notification(
            message = message,
            sender = sender,
            receiver = receiver
        )
    }
}