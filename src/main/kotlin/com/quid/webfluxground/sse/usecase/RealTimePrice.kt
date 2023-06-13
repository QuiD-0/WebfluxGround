package com.quid.webfluxground.sse.usecase

import com.quid.webfluxground.sse.domain.Stock
import com.quid.webfluxground.sse.gateway.repository.StockRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

interface RealTimePrice {

    fun byCode(code: Mono<String>): Mono<Stock>

    @Service
    class RealTimePriceUseCase(
        private val stockRepository: StockRepository,
    ) : RealTimePrice {

        override fun byCode(code: Mono<String>): Mono<Stock> =
            stockRepository.findByCode(code)
                .map { it.updatePrice() }
                .flatMap { stockRepository.save(it) }
    }
}