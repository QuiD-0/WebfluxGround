package com.quid.webfluxground.sse_stock.gateway.repository

import com.quid.webfluxground.sse_stock.domain.Stock
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface StockRepository {

    fun findByCode(code: Mono<String>): Mono<Stock>
    fun save(stock: Stock): Stock
    fun findAll(): Flux<Stock>
}