package com.quid.webfluxground.stock.domain

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
        require(currency.isNotBlank()) { "currency must not be blank" }
        require(code.isNotBlank()) { "code must not be blank" }
    }

    fun updatePrice(): Stock =
        copy(price = getRandomPrice(price)).also { it.addPriceLog(price) }

    private fun addPriceLog(price: BigDecimal): Unit = previousPrice.let {
        it.add(price)
        if (it.size > 5) it.removeAt(0)
    }

    private fun getRandomPrice(price: BigDecimal) =
        Random.nextDouble(-10.0, 10.0).let {
            price + BigDecimal(it).setScale(2, RoundingMode.HALF_UP)
        }

    private fun clonePriceLog() = ArrayList<BigDecimal>(previousPrice)

    fun getDelta(): String =previousPrice.lastOrNull()?.let { price - it }?.toString() ?: "0"

    private fun copy(
        name: String = this.name,
        price: BigDecimal = this.price,
        currency: String = this.currency,
    ) = Stock(name, price.abs(), currency, code, LocalDateTime.now(), clonePriceLog())

    override fun toString(): String {
        return "Stock(name='$name', price=$price, currency='$currency', code='$code', timestamp=$timestamp, previousPrice=$previousPrice)"
    }
}