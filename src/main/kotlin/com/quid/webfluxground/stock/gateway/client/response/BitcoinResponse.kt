package com.quid.webfluxground.stock.gateway.client.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.quid.webfluxground.stock.domain.Stock
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


data class BitcoinResponse(
    @JsonProperty("currency")
    val currency: String,
    @JsonProperty("errorCode")
    val errorCode: String,
    @JsonProperty("first")
    val first: String,
    @JsonProperty("high")
    val high: String,
    @JsonProperty("last")
    val last: String,
    @JsonProperty("low")
    val low: String,
    @JsonProperty("result")
    val result: String,
    @JsonProperty("timestamp")
    val timestamp: String,
    @JsonProperty("volume")
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

    private val datetime: LocalDateTime
        get() = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp.toLong()), ZoneId.systemDefault())

    fun toStock() = Stock(
        name = "Bitcoin",
        code = "BTC",
        price = BigDecimal(last),
        currency = currency,
        timestamp = datetime
    )
}

