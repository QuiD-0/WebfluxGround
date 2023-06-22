package com.quid.webfluxground.notification.handler

import com.quid.webfluxground.notification.domain.Notification
import org.springframework.stereotype.Service
import java.util.function.Consumer

interface NotificationHandler {
    fun subscribe(id:String, listener: Consumer<Notification>)
    fun publish(notification: Notification)

    @Service
    class MemoryNotificationHandler : NotificationHandler {
        private val listeners: MutableMap<String, List<Consumer<Notification>>> = hashMapOf()

        override fun subscribe(id:String, listener: Consumer<Notification>) {
            listeners[id] = listeners[id]?.plus(listener) ?: listOf(listener)
        }

        override fun publish(notification: Notification) {
            notification.receiver.let {
                listeners[it]?.forEach { listener -> listener.accept(notification) }
            }
        }
    }
}