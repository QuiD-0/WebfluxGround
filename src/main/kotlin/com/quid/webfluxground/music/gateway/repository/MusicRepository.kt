package com.quid.webfluxground.music.gateway.repository

import com.quid.webfluxground.music.domain.Music
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface MusicRepository {
    fun findAll(): Flux<Music>
    fun save(music: Music): Mono<Music>

    @Repository
    class MusicRepositoryImpl(
        private val musicMongoRepository: MusicMongoRepository
    ): MusicRepository {
        override fun findAll(): Flux<Music> {
            return musicMongoRepository.findAll()
                .map { it.toDomain() }
        }

        override fun save(music: Music): Mono<Music> {
            return musicMongoRepository.save(document(music))
                .map { it.toDomain() }
        }

    }
}