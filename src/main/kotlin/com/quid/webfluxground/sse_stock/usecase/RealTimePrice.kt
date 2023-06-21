package com.quid.webfluxground.sse_stock.usecase

import com.quid.webfluxground.sse_stock.domain.Stock
import com.quid.webfluxground.sse_stock.gateway.repository.StockRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface RealTimePrice {

    fun byCode(code: Mono<String>): Mono<Stock>
    fun all(): Flux<Stock>

    @Service
    class RealTimePriceUseCase(
        private val stockRepository: StockRepository,
    ) : RealTimePrice {

        override fun byCode(code: Mono<String>): Mono<Stock> =
            stockRepository.findByCode(code)
                .map { it.updatePrice() }
                .map { stockRepository.save(it) }

        override fun all(): Flux<Stock> {
            return stockRepository.findAll()
                .flatMap { Mono.just(it.updatePrice()) }
                .flatMap { Mono.just(stockRepository.save(it)) }
        }
    }
}