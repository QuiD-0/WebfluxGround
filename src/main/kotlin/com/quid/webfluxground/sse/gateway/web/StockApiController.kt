package com.quid.webfluxground.sse.gateway.web

import com.quid.webfluxground.sse.domain.Stock
import com.quid.webfluxground.sse.usecase.FindStock
import com.quid.webfluxground.sse.usecase.RealTimePrice
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/stock")
class StockApiController(
    private val findStock: FindStock,
    private val realTimePrice: RealTimePrice
) {

    @GetMapping("/find/{code}")
    fun findStock(@PathVariable code: String) = findStock.byCode(Mono.just(code))

    @GetMapping("/price/{code}")
    fun realTimePrice(@PathVariable code: String): Flux<Stock> = Flux.interval(java.time.Duration.ofSeconds(1))
        .flatMap { realTimePrice.byCode(Mono.just(code)) }

}