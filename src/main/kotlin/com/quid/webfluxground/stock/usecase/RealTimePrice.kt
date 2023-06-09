package com.quid.webfluxground.stock.usecase

import com.quid.webfluxground.stock.domain.Stock
import com.quid.webfluxground.stock.gateway.client.BitcoinGateway
import com.quid.webfluxground.stock.gateway.repository.StockRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface RealTimePrice {

    fun byCode(code: Mono<String>): Mono<Stock>
    fun all(): Flux<Stock>
    fun btc(): Flux<Stock>

    @Service
    class RealTimePriceUseCase(
        private val stockRepository: StockRepository,
        private val bitcoinGateway: BitcoinGateway
    ) : RealTimePrice {

        override fun byCode(code: Mono<String>): Mono<Stock> =
            stockRepository.findByCode(code)
                .map { it.updatePrice() }
                .map { stockRepository.save(it) }

        override fun all(): Flux<Stock> {
            return stockRepository.findAll()
                .flatMap { Mono.just(it.updatePrice()) }
                .flatMap { Mono.just(stockRepository.save(it)) }
        }

        override fun btc(): Flux<Stock> {
            return bitcoinGateway.price()
        }
    }
}