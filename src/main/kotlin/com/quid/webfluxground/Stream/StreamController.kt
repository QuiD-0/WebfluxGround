package com.quid.webfluxground.Stream

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.*
import java.util.stream.Stream


@RestController("/stream")
class StreamController {

    @GetMapping("/sse")
    fun stream(): Flux<MutableMap<String, Int>> =
        Stream.iterate(0) { e: Int -> e + 1 }.let {
            Flux.fromStream(it)
                .map { i: Int -> Collections.singletonMap("value", i) }
                .delayElements(Duration.ofSeconds(1))
        }

    @PostMapping("/postMono")
    fun echo(@RequestBody body: Mono<String>): Mono<String> =
        body.map { it.uppercase(Locale.getDefault()) }

    @PostMapping("/postFlux")
    fun echo(@RequestBody body: Flux<Map<String,Int>>) : Flux<MutableMap<String,Int>> =
        body.mapNotNull { it["value"]?.times(2) }
                .map { Collections.singletonMap("double", it) }

}