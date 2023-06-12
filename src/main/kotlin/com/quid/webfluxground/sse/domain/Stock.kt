package com.quid.webfluxground.sse.domain

import java.time.LocalDateTime
import kotlin.random.Random

class Stock(
    val name: String,
    val price: Double,
    val currency: String,
    val code: String,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val previousPrice: ArrayList<Double> = arrayListOf(),
) {
    init {
        require(name.isNotBlank()) { "name must not be blank" }
        require(price > 0) { "price must be greater than 0" }
        require(currency.isNotBlank()) { "currency must not be blank" }
        require(code.isNotBlank()) { "code must not be blank" }
    }

    fun updatePrice() = copy(price = Random.nextDouble(10.0, 100.0))
        .also { it.addPriceLog(price) }

    private fun addPriceLog(price: Double) = previousPrice.apply { add(price) }

    private fun copy(
        name: String = this.name,
        price: Double = this.price,
        currency: String = this.currency,
    ) = Stock(name, price, currency, code, LocalDateTime.now(), previousPrice.clone() as ArrayList<Double>)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Stock

        if (code != other.code) return false

        return true
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }
}

fun createStock(
    name: String,
    price: Double,
    currency: String,
    code: String,
) = Stock(name, price, currency, code)