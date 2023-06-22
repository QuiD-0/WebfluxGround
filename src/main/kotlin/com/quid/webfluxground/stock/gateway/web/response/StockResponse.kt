package com.quid.webfluxground.stock.gateway.web.response

import com.quid.webfluxground.stock.domain.Stock
import reactor.core.publisher.Mono
import java.time.LocalDateTime

data class StockResponse(
    val name: String,
    val code: String,
    val price: String,
    val timestamp: LocalDateTime,
    val delta : String = ""
)

fun toResponse(stock: Stock) = Mono.just(
    StockResponse(
        name = stock.name,
        code = stock.code,
        price = stock.price.toString() + " " + stock.currency,
        timestamp = stock.timestamp,
        delta = stock.getDelta()
    )
)