package com.quid.webfluxground.push.handler

import com.quid.webfluxground.push.domain.Push
import org.springframework.stereotype.Service
import java.util.function.Consumer

interface PushHandler {
    fun subscribe(listener: Consumer<Push>)
    fun publish(push: Push)

    @Service
    class MemoryPushHandler : PushHandler {
        private val listeners: MutableList<Consumer<Push>> = arrayListOf()

        override fun subscribe(listener: Consumer<Push>) {
            listeners.add(listener)
        }

        override fun publish(push: Push) {
            listeners.forEach { listener -> listener.accept(push) }
        }
    }
}