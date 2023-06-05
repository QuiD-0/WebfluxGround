package com.quid.webfluxground.Mono_Flux

import reactor.core.publisher.Mono
import java.time.Duration

class MonoGround {
    fun mono() {
        val mono = Mono.just("Hello")
        mono.subscribe(::println)
    }

    fun mono2(): Mono<String> {
        return Mono.just("Hello")
    }

    fun mono3(): Mono<String> {
        return Mono.just("Hello").delayElement(Duration.ofSeconds(1))
    }
}

fun main() {
    val monoGround = MonoGround()

    monoGround.mono()

    println(monoGround.mono2().block())

    monoGround.mono2().map { "$it World" }.subscribe(::println)

    println(monoGround.mono3().block())

    println(monoGround.mono3()
        .map { "$it World" }
        .map { "$it!!" }
        .block())
}