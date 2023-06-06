package com.quid.webfluxground.Stream

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.util.*
import java.util.stream.Stream


@RestController("/stream")
class StreamController {

    @GetMapping("/sse")
    fun stream(): Flux<MutableMap<String, Int>> {
        val stream = Stream.iterate(0) { i: Int -> i + 1 }

        return Flux.fromStream(stream)
                .map { i: Int -> Collections.singletonMap("value", i) }
                .delayElements(java.time.Duration.ofSeconds(1))
    }

}