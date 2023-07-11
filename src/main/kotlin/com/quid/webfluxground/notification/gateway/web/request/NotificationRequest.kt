package com.quid.webfluxground.notification.gateway.web.request

import com.quid.webfluxground.notification.domain.Notification

data class NotificationRequest(val title:String, val message: String, val sender: String, val receiver: String) {
    fun toNotification() = Notification.create(title, message, sender, receiver)
}
