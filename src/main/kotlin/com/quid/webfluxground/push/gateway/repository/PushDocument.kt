package com.quid.webfluxground.push.gateway.repository

import com.quid.webfluxground.push.domain.Push
import org.bson.types.ObjectId
import java.time.LocalDateTime

class PushDocument(
    val id: ObjectId,
    val message: String,
    val sender: String,
    val receiver: String,
    val timestamp: LocalDateTime
) {
    fun toDomain() : Push{
        return Push(
            id = id.toHexString(),
            message = message,
            sender = sender,
            receiver = receiver,
            timestamp = timestamp
        )
    }

    companion object{
        fun document(push: Push) = PushDocument(
            id = ObjectId(push.id),
            message = push.message,
            sender = push.sender,
            receiver = push.receiver,
            timestamp = push.timestamp
        )
    }
}