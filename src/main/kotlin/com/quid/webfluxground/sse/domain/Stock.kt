package com.quid.webfluxground.sse.domain

import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import kotlin.random.Random

class Stock(
    val name: String,
    val price: BigDecimal,
    val currency: String,
    val code: String,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val previousPrice: ArrayList<BigDecimal> = arrayListOf(),
) {
    init {
        require(name.isNotBlank()) { "name must not be blank" }
        require(price > BigDecimal.ZERO) { "price must be greater than 0" }
        require(currency.isNotBlank()) { "currency must not be blank" }
        require(code.isNotBlank()) { "code must not be blank" }
    }

    fun updatePrice() = copy(
        price = price + Random.nextDouble(-10.0, 10.0).toBigDecimal().setScale(2, RoundingMode.HALF_UP)
    ).also { it.addPriceLog(price) }

    private fun addPriceLog(price: BigDecimal): Unit = previousPrice.let {
        it.add(price)
        if (it.size > 5) it.removeAt(0)
    }

    private fun copy(
        name: String = this.name,
        price: BigDecimal = this.price,
        currency: String = this.currency,
    ) = Stock(name, price, currency, code, LocalDateTime.now(), previousPrice.clone() as ArrayList<BigDecimal>)

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
    price: BigDecimal,
    currency: String,
    code: String,
) = Stock(name, price, currency, code)