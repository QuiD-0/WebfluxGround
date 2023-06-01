package com.quid.webfluxground.music.gateway.repository

import org.bson.types.ObjectId
import org.springframework.data.repository.CrudRepository

interface MusicMongoRepository: CrudRepository<MusicDocument, ObjectId> {
    fun existsByTitleAndArtist(title: String, artist: String): Boolean
}