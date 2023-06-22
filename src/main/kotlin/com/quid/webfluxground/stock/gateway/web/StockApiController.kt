package com.quid.webfluxground.stock.gateway.web

import com.quid.webfluxground.stock.gateway.web.response.StockResponse
import com.quid.webfluxground.stock.gateway.web.response.toResponse
import com.quid.webfluxground.stock.usecase.FindStock
import com.quid.webfluxground.stock.usecase.RealTimePrice
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@RestController
@RequestMapping("/api/stock")
class StockApiController(
    private val findStock: FindStock,
    private val realTimePrice: RealTimePrice,
) {

    @GetMapping("/find/{code}")
    fun findStock(@PathVariable code: String): Mono<StockResponse> =
        findStock.byCode(Mono.just(code))
            .flatMap(::toResponse)

    @GetMapping("/price/{code}")
    fun realTimePrice(@PathVariable code: String): Flux<StockResponse> =
        Flux.interval(Duration.ofSeconds(1))
            .flatMap { realTimePrice.byCode(Mono.just(code)) }
            .flatMap(::toResponse)

    @GetMapping("/price")
    fun realTimePrice(): Flux<StockResponse> =
        Flux.interval(Duration.ofSeconds(1))
            .flatMap { realTimePrice.all() }
            .flatMap(::toResponse)

    @GetMapping("/all")
    fun all(): Flux<StockResponse> =
        realTimePrice.all()
            .flatMap(::toResponse)
}