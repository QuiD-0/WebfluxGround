package com.quid.webfluxground.music.gateway.repository

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface MusicMongoRepository: ReactiveMongoRepository<MusicDocument, ObjectId> {
}