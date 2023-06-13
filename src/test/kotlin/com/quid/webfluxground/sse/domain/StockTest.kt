package com.quid.webfluxground.sse.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StockTest{

    @Test
    fun deepCopy(){
        val origin = arrayListOf(1,2,3)
        val copy = ArrayList<Int>(origin)

        origin.add(6)
        copy.add(7)

        assertNotEquals(origin, copy)
    }

    @Test
    fun updatePrice(){
        val stock = Stock("name", 100.toBigDecimal(), "USD", "code")
        val updatedStock = stock.updatePrice()

        assertNotEquals(stock.price, updatedStock.price)
    }

    @Test
    fun updatePriceLog(){
        val stock = Stock("name", 100.toBigDecimal(), "USD", "code")
        val updatedStock = stock.updatePrice()

        assertEquals(updatedStock.previousPrice.size, 1)
    }
}