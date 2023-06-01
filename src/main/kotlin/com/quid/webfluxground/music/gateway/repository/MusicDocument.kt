package com.quid.webfluxground.music.gateway.repository

import com.quid.webfluxground.music.domain.Music
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
class MusicDocument(
    @Id
    private val id: ObjectId,
    @Indexed private val title: String,
    private val artist: String,
    private val album: String,
    private val genre: String,
    private val duration: Int,
) {
    fun toDomain(): Music {
        return Music(
            id = id.toHexString(),
            title = title,
            artist = artist,
            album = album,
            genre = genre,
            duration = duration,
        )
    }
}

fun document(music: Music) = MusicDocument(
    id = music.id?.let { ObjectId(it) } ?: ObjectId(),
    title = music.title,
    artist = music.artist,
    album = music.album,
    genre = music.genre,
    duration = music.duration,
)