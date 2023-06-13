package com.quid.webfluxground.sse.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StockTest{

    @Test
    fun deepCopy(){
        val list1 = listOf(1, 2, 3, 4, 5).toCollection(ArrayList())
        val list2 = list1.stream().toList().toCollection(ArrayList())

        list1.add(6)
        list2.add(7)

        assertNotEquals(list1, list2)
    }
}