package com.quid.webfluxground.music.gateway.repository

import com.quid.webfluxground.music.domain.Music
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

interface MusicRepository {
    fun findAll(): Flux<Music>

    @Repository
    class MusicRepositoryImpl(
        private val musicMongoRepository: MusicMongoRepository
    ): MusicRepository {
        override fun findAll(): Flux<Music> {
            return musicMongoRepository.findAll()
                .map { it.toDomain() }
        }

    }
}