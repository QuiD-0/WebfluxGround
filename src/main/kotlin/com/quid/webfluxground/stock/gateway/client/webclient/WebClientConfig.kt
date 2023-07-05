package com.quid.webfluxground.stock.gateway.client.webclient

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono


@Configuration
class WebClientConfig {

    @Bean(name = ["bitcoinWebClient"])
    fun webClient(): WebClient {

        return WebClient.builder()
            .baseUrl("https://api.coinone.co.kr")
            .clientConnector(ReactorClientHttpConnector(DefaultHttpClient.create()))
            .filter(
                ExchangeFilterFunction.ofRequestProcessor {
                    println("Request: ${it.method()} ${it.url()}")
                    Mono.just(it)
                }
            )
            .filter(
                ExchangeFilterFunction.ofResponseProcessor {
                    println("Response: ${it.statusCode()}")
                    Mono.just(it)
                }
            )
            .defaultHeaders {
                it.set("Content-Type", "application/json")
                it.set("Accept", "application/json")
            }
            .build()
    }
}