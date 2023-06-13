package com.quid.webfluxground.sse.gateway.repository

import com.quid.webfluxground.sse.domain.Stock
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Repository
class MemoryStockRepository : StockRepository {
    private val stocks: MutableMap<String, Stock> = mutableMapOf(
        "AAPL" to Stock("Apple", BigDecimal(100.0), "USD", "AAPL"),
        "GOOG" to Stock("Google", BigDecimal(200.0), "USD", "GOOG"),
        "AMZN" to Stock("Amazon", BigDecimal(300.0), "USD", "AMZN"),
    )

    override fun findByCode(code: Mono<String>): Mono<Stock> = code.map { stocks[it]?: throw IllegalArgumentException("Stock not found") }

    override fun save(stock: Stock): Mono<Stock> = Mono.just(stock).also { stocks[stock.code] = stock }

}