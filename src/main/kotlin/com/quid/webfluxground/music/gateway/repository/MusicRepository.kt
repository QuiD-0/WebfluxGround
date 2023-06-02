package com.quid.webfluxground.music.gateway.repository

import com.quid.webfluxground.music.domain.Music
import org.bson.types.ObjectId
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

interface MusicRepository {
    fun findAll(): Flux<Music>
    fun save(music: Music): Mono<Music>
    fun checkDuplicate(music: Music): Mono<Boolean>
    fun findById(id: String): Mono<Music>

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

        override fun findById(id: String): Mono<Music> {
            return reactiveMusicMongoRepository.findById(ObjectId(id))
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(IllegalArgumentException("Music not found")))
                .map { it.toDomain() }

        }
    }
}