package com.quid.webfluxground.push.gateway.controller

import com.quid.webfluxground.push.domain.Push
import com.quid.webfluxground.push.handler.PushHandler
import org.springframework.http.HttpStatus
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/api/push")
class PushController(
    private val pushHandler: PushHandler
) {

    @GetMapping("/subscribe/{id}")
    fun consumer(@PathVariable id: String): Flux<ServerSentEvent<Push>> = Flux.create {
        pushHandler.subscribe(id) { push ->
            it.next(
                ServerSentEvent.builder<Push>()
                    .id(push.id)
                    .event("push")
                    .data(push)
                    .build()
            )
        }
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    fun send(@RequestBody push: Push): Mono<Push> =
        Mono.just(push).doOnNext(pushHandler::publish)
}