package com.quid.webfluxground.stream

import com.quid.webfluxground.stream.request.PostRequest
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

    /**
     * curl -i localhost:8080/sse -H 'Accept: text/event-stream'
     */
    @GetMapping("/sse")
    fun stream(): Flux<MutableMap<String, Int>> =
        Stream.iterate(0) { e: Int -> e + 1 }.let {
            Flux.fromStream(it)
                .map { i: Int -> Collections.singletonMap("value", i) }
                .delayElements(Duration.ofSeconds(1))
        }

    /**
     * curl -i localhost:8080/postSse
     * -d '{"data":1}{"data":2}{"data":3}'
     * -H 'Content-Type: application/stream+json'
     * -H 'Accept: text/event-stream'
     *
     * */
    @PostMapping("/postSse")
    fun postStream(@RequestBody req: Flux<PostRequest>) : Flux<MutableMap<String,Int>> =
        Stream.iterate(0){ e: Int -> e+1 }.let { data ->
            Flux.fromStream(data)
                .zipWith(req.map { it.data })
                .map { it.t1 + it.t2 }
                .map { Collections.singletonMap("value", it ) }
                .delayElements(Duration.ofSeconds(1))
        }
    /**
     * curl -i localhost:8080/postMono -H 'Content-Type: application/json' -d quid
     */
    @PostMapping("/postMono")
    fun echo(@RequestBody body: Mono<String>): Mono<String> =
        body.map { it.uppercase(Locale.getDefault()) }

    /**
     * curl -i localhost:8080/postFlux
     * -d '{"value":1}{"value":2}{"value":3}'
     * -H 'Content-Type: application/stream+json'
     * -H 'Accept: text/event-stream'
     *
     * */
    @PostMapping("/postFlux")
    fun echo(@RequestBody body: Flux<Map<String,Int>>) : Flux<MutableMap<String,Int>> =
        body.mapNotNull { it["value"]?.times(2) }
                .map { Collections.singletonMap("double", it) }

}

