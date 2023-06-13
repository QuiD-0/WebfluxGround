package com.quid.webfluxground.sse.usecase

import com.quid.webfluxground.sse.domain.Stock
import com.quid.webfluxground.sse.gateway.repository.StockRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

interface FindStock {

    fun byCode(code: Mono<String>): Mono<Stock>

    @Service
    class FindStockUseCase(
        private val stockRepository: StockRepository
    ) : FindStock {

        override fun byCode(code: Mono<String>): Mono<Stock> = stockRepository.findByCode(code)
    }
}