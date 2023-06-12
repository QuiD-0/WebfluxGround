package com.quid.webfluxground.mono_flux

import reactor.core.publisher.Flux
import kotlin.random.Random

class MapGround {

    private val randomList: List<Int> = List(10000000) { Random.nextInt(0, 1000) }
    private val flux = Flux.defer { Flux.fromIterable(randomList) }

    fun map() {
        val timer = System.currentTimeMillis()
        flux.map { "$it map" }.subscribe()
        println("map: ${System.currentTimeMillis() - timer}")
    }

    fun flatMap() {
        val timer = System.currentTimeMillis()
        flux.flatMap { Flux.just("$it flatMap") }.subscribe()
        println("flatMap: ${System.currentTimeMillis() - timer}")
    }

}

fun main() {
    val mapGround = MapGround()

    mapGround.map()

    mapGround.flatMap()
}