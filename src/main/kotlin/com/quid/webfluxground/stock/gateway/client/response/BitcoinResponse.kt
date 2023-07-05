package com.quid.webfluxground.stock.gateway.client.response

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES
import com.fasterxml.jackson.annotation.JsonProperty
import com.quid.webfluxground.stock.domain.Stock
import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneId


data class BitcoinResponse(
    val currency: String,
    val errorCode: String,
    val first: String,
    val high: String,
    val last: String,
    val low: String,
    val result: String,
    val timestamp: String,
    val volume: String,
    @JsonProperty("yesterday_first")
    val yesterdayFirst: String,
    @JsonProperty("yesterday_high")
    val yesterdayHigh: String,
    @JsonProperty("yesterday_last")
    val yesterdayLast: String,
    @JsonProperty("yesterday_low")
    val yesterdayLow: String,
    @JsonProperty("yesterday_volume")
    val yesterdayVolume: String
){

    fun toStock() = Stock(
        name = "Bitcoin",
        code = "BTC",
        price = BigDecimal(last),
        currency = currency,
        timestamp = Instant.ofEpochMilli(timestamp.toLong() * 1000).atZone(ZoneId.systemDefault()).toLocalDateTime()
    )
}

