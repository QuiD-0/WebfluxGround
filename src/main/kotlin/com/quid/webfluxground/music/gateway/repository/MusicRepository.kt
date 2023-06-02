package com.quid.webfluxground.music.gateway.repository

import com.quid.webfluxground.music.domain.Music
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface MusicRepository {
    fun findAll(): Flux<Music>
    fun save(music: Music): Mono<Music>
    fun checkDuplicate(music: Music): Mono<Boolean>

    @Repository
    class MusicRepositoryImpl(
        private val reactiveMusicMongoRepository: ReactiveMusicMongoRepository,
    ) : MusicRepository {
        override fun findAll(): Flux<Music> {
            return reactiveMusicMongoRepository.findAll().map { it.toDomain() }
        }

        override fun save(music: Music): Mono<Music> {
            return reactiveMusicMongoRepository.save(document(music)).map { it.toDomain() }
        }

        override fun checkDuplicate(music: Music): Mono<Boolean> {
            return reactiveMusicMongoRepository.existsByTitleAndArtist(music.title, music.artist)
        }
    }
}