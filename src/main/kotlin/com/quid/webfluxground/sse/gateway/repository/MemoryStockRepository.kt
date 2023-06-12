package com.quid.webfluxground.sse.gateway.repository

import com.quid.webfluxground.sse.domain.Stock
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class MemoryStockRepository : StockRepository {
    private val stocks: MutableMap<String, Stock> = mutableMapOf(
        "AAPL" to Stock("Apple", 100.0, "USD", "AAPL"),
        "GOOG" to Stock("Google", 200.0, "USD", "GOOG"),
        "AMZN" to Stock("Amazon", 300.0, "USD", "AMZN"),
    )

    override fun findByCode(code: Mono<String>): Mono<Stock> {
        return code.map { stocks[it]?: throw IllegalArgumentException("Stock not found") }
    }

    override fun save(stock: Stock): Mono<Stock> {
        stocks[stock.code] = stock
        return Mono.just(stock)
    }

}