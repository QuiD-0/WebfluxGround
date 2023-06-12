package com.quid.webfluxground.sse.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class StockTest {

    @Test
    fun copy() {
        createStock(
            name = "name", price = 1.0, currency = "currency", code = "code"
        ).let {
            assertNotEquals(it.price, it.updatePrice().price)
        }
    }

    @Test
    fun equals() {
        createStock(
            name = "name", price = 1.0, currency = "currency", code = "code"
        ).let {
            assertEquals(it, it.updatePrice())
        }
    }

    @Test
    fun updateLog() {
        val stock = createStock(
            name = "name", price = 1.0, currency = "currency", code = "code"
        ).updatePrice().updatePrice().updatePrice()
        println(stock.previousPrice)
        println(stock.price)
    }
}