package com.quid.webfluxground.music.gateway.web.dto

import com.quid.webfluxground.music.domain.Music
import com.quid.webfluxground.music.domain.createMusic

data class MusicCreateRequest(
    val title: String,
    val artist: String,
    val album: String,
    val genre: String,
    val duration: Int,
) {
    fun toMusic(): Music {
        return createMusic(
            title = title,
            artist = artist,
            album = album,
            genre = genre,
            duration = duration)
    }
}

data class MusicUpdateRequest(
    val id: String,
    val title: String,
    val artist: String,
)

data class MusicDeleteRequest(
    val id: String)