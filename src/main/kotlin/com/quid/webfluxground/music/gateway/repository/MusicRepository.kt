package com.quid.webfluxground.music.gateway.repository

import com.quid.webfluxground.music.domain.Music
import com.quid.webfluxground.music.gateway.repository.mongo.MusicReactiveMongoRepository
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
    fun delete(id: String)
    fun deleteBy(title: String, artist: String)

    @Repository
    class MusicRepositoryImpl(
        private val musicReactiveMongoRepository: MusicReactiveMongoRepository,
    ) : MusicRepository {
        override fun findAll(): Flux<Music> {
            return musicReactiveMongoRepository.findAll().map { it.toDomain() }
        }

        override fun save(music: Music): Mono<Music> {
            return musicReactiveMongoRepository.save(document(music)).map { it.toDomain() }
        }

        override fun checkDuplicate(music: Music): Mono<Boolean> {
            return musicReactiveMongoRepository.existsByTitleAndArtist(music.title, music.artist)
        }

        override fun findById(id: String): Mono<Music> {
            return musicReactiveMongoRepository.findById(ObjectId(id))
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(IllegalArgumentException("Music not found")))
                .map { it.toDomain() }

        }

        override fun delete(id: String) {
            musicReactiveMongoRepository.deleteById(ObjectId(id)).subscribe()
        }

        override fun deleteBy(title: String, artist: String) {
            musicReactiveMongoRepository.deleteByTitleAndArtist(title, artist).subscribe()
        }
    }
}