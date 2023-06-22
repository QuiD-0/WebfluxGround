package com.quid.webfluxground.music.gateway.repository.mongo

import com.quid.webfluxground.music.gateway.repository.MusicDocument
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface MusicReactiveMongoRepository : ReactiveMongoRepository<MusicDocument, ObjectId> {
    fun existsByTitleAndArtist(title: String, artist: String): Mono<Boolean>
    fun deleteByTitleAndArtist(title: String, artist: String): Mono<Unit>
}