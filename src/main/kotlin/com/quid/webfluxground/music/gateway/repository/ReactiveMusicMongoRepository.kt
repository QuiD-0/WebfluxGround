package com.quid.webfluxground.music.gateway.repository

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface ReactiveMusicMongoRepository : ReactiveMongoRepository<MusicDocument, ObjectId> {
    fun existsByTitleAndArtist(title: String, artist: String): Mono<Boolean>
    fun deleteByTitleAndArtist(title: String, artist: String): Mono<Unit>
}