package com.quid.webfluxground.sse.gateway.web

import com.quid.webfluxground.sse.domain.Stock
import reactor.core.publisher.Mono
import java.time.LocalDateTime

data class StockResponse(
    val name: String,
    val code: String,
    val price: String,
    val timestamp: LocalDateTime,
)

fun toResponse(stock: Stock) = Mono.just(
    StockResponse(
        name = stock.name,
        code = stock.code,
        price = stock.price.toString() + " " + stock.currency,
        timestamp = stock.timestamp
    )
)