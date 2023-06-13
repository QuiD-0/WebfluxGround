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

    fun updatePrice(): Stock =
        copy(price = price + getRandomPrice()).also { it.addPriceLog(price) }

    private fun addPriceLog(price: BigDecimal): Unit = previousPrice.let {
        it.add(price)
        if (it.size > 5) it.removeAt(0)
    }

    private fun getRandomPrice() =
        BigDecimal(Random.nextDouble(-10.0, 10.0)).setScale(2, RoundingMode.HALF_UP)

    private fun clonePriceLog() = ArrayList<BigDecimal>(previousPrice)

    fun getDelta(): String =previousPrice.lastOrNull()?.let { price - it }?.toString() ?: "0"

    private fun copy(
        name: String = this.name,
        price: BigDecimal = this.price,
        currency: String = this.currency,
    ) = Stock(name, price, currency, code, LocalDateTime.now(), clonePriceLog())

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