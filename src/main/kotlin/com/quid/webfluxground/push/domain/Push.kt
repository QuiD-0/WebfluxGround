package com.quid.webfluxground.push.domain

import org.bson.types.ObjectId
import java.time.LocalDateTime

class Push(
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
        ): Push = Push(
            message = message,
            sender = sender,
            receiver = receiver
        )
    }
}