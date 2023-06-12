package com.quid.webfluxground.mono_flux

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

class FluxGround {
    fun flux() {
        val flux = Flux.just("Hello", "World")
        flux.subscribe(::println)
    }

    fun flux2(): Flux<String> {
        return Flux.just("Hello", "World")
    }

    fun flux3(): Flux<String> {
        return Flux.just("Hello", "World").delayElements(Duration.ofSeconds(1))
    }
}

fun main() {
    val fluxGround = FluxGround()

    fluxGround.flux()

    println(fluxGround.flux2().blockFirst())

    fluxGround.flux2().subscribe(::println)

    fluxGround.flux2().flatMap { Flux.just("$it World!") }.subscribe(::println)

    println(fluxGround.flux3().blockFirst())

    println(fluxGround.flux3()
        .flatMap { Mono.just("$it mono") }
        .blockLast())

}