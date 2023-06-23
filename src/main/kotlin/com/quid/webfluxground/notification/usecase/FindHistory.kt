package com.quid.webfluxground.notification.usecase

import com.quid.webfluxground.notification.domain.Notification
import com.quid.webfluxground.notification.gateway.repository.NotificationRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

interface FindHistory {

    fun byReceiver(receiver: String): Flux<Notification>

    @Service
    class FindHistoryImpl(
        private val repository: NotificationRepository
    ) : FindHistory {

        override fun byReceiver(receiver: String): Flux<Notification> =
            repository.findByReceiver(receiver)
    }
}