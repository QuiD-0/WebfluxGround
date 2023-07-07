package com.quid.webfluxground.stock.gateway.client

import com.quid.webfluxground.stock.domain.Stock
import com.quid.webfluxground.stock.gateway.client.response.BitcoinResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

interface BitcoinGateway {
    fun price(): Flux<Stock>

    @Component
    class WebClientBitcoinGateway(
        @Qualifier("bitcoinWebClient") private val webClient: WebClient
    ) : BitcoinGateway {

        override fun price(): Flux<Stock> = webClient.get()
            .uri("/ticker")
            .retrieve()
            .bodyToFlux(BitcoinResponse::class.java)
            .map { it.toStock() }
            .log()
    }
}