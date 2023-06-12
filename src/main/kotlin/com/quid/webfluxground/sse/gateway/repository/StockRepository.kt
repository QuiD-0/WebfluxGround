package com.quid.webfluxground.sse.gateway.repository

import com.quid.webfluxground.sse.domain.Stock
import reactor.core.publisher.Mono

interface StockRepository {

    fun findByCode(code: Mono<String>): Mono<Stock>

    fun save(stock: Stock): Mono<Stock>
}