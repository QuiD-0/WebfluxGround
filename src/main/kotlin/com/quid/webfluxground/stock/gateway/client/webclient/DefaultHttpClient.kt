package com.quid.webfluxground.stock.gateway.client.webclient

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.Base64.Decoder
import java.util.concurrent.TimeUnit

class DefaultHttpClient {

    companion object {
        fun create(): HttpClient =
            HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                .responseTimeout(Duration.ofMillis(1000))
                .doOnConnected { conn ->
                    conn.addHandlerLast(ReadTimeoutHandler(1000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(WriteTimeoutHandler(1000, TimeUnit.MILLISECONDS))
                }
    }
}