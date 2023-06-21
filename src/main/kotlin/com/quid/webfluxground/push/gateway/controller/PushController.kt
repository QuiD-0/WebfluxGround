package com.quid.webfluxground.push.gateway.controller

import com.quid.webfluxground.push.domain.Push
import com.quid.webfluxground.push.handler.PushHandler
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/api/push")
class PushController(
    private val pushHandler: PushHandler
) {

    @GetMapping(path = ["/subscribe"], produces = [TEXT_EVENT_STREAM_VALUE])
    fun consumer(): Flux<ServerSentEvent<Push>> {
        return Flux.create { sink: FluxSink<ServerSentEvent<Push>> ->
            pushHandler.subscribe { push ->
                sink.next(
                    ServerSentEvent.builder<Push>()
                        .id(push.id)
                        .event("push")
                        .data(push)
                        .build()
                )
            }
        }
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    fun send(@RequestBody push: Push): Mono<Push> {
        pushHandler.publish(push)
        return Mono.just(push)
    }
}