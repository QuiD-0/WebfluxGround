package com.quid.webfluxground.music.gateway.web.dto

import com.quid.webfluxground.music.domain.Music

data class MusicResponse(
    val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val genre: String,
    val duration: String,
    ){
}

fun toMusicResponse(music: Music): MusicResponse {
    val playTime = "${music.duration / 60}:${music.duration % 60}"
    return MusicResponse(music.id!!, music.title, music.artist, music.album, music.genre, playTime)
}