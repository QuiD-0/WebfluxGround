package com.quid.webfluxground.notification.gateway.web

import com.quid.webfluxground.notification.domain.Notification
import com.quid.webfluxground.notification.gateway.event.producer.PushProducer
import com.quid.webfluxground.notification.gateway.web.request.NotificationRequest
import com.quid.webfluxground.notification.handler.NotificationHandler
import org.springframework.http.HttpStatus
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/api/push")
class NotificationController(
    private val notificationHandler: NotificationHandler,
    private val pushProducer: PushProducer
) {

    @GetMapping("/subscribe/{id}")
    fun connect(@PathVariable id: String): Flux<ServerSentEvent<Notification>> = Flux.create {
        notificationHandler.subscribe(id) { push ->
            it.next(
                ServerSentEvent.builder<Notification>()
                    .id(push.id)
                    .event("notification")
                    .data(push)
                    .build()
            )
        }
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    fun send(@RequestBody request: NotificationRequest) =
        request.toNotification().let {
            pushProducer.publish(it)
        }
}