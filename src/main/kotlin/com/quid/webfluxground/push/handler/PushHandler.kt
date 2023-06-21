package com.quid.webfluxground.push.handler

import com.quid.webfluxground.push.domain.Push
import org.springframework.stereotype.Service
import java.util.function.Consumer

interface PushHandler {
    fun subscribe(id:String, listener: Consumer<Push>)
    fun publish(push: Push)

    @Service
    class MemoryPushHandler : PushHandler {
        private val listeners: MutableMap<String, List<Consumer<Push>>> = hashMapOf()

        override fun subscribe(id:String, listener: Consumer<Push>) {
            listeners[id] = listeners[id]?.plus(listener) ?: listOf(listener)
        }

        override fun publish(push: Push) {
            push.receiver.let {
                listeners[it]?.forEach { listener -> listener.accept(push) }
            }
        }
    }
}