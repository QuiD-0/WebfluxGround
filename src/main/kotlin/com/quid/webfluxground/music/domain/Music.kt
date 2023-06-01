package com.quid.webfluxground.music.domain

class Music(
    val id: String? = null,
    val title: String,
    val artist: String,
    val album: String,
    val genre: String,
    val duration: Int,
) {
    init {
        require(title.isNotBlank()) { "Title must not be blank" }
        require(artist.isNotBlank()) { "Artist must not be blank" }
        require(album.isNotBlank()) { "Album must not be blank" }
        require(genre.isNotBlank()) { "Genre must not be blank" }
        require(duration > 0) { "Duration must be greater than 0" }
    }

    fun copy(
        id: String? = this.id,
        title: String = this.title,
        artist: String = this.artist,
        album: String = this.album,
        genre: String = this.genre,
        duration: Int = this.duration,
    ): Music {
        return Music(id,title,artist,album, genre,duration)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Music) return false
        return other.id == id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}

fun createMusic(
    title: String,
    artist: String,
    album: String,
    genre: String,
    duration: Int,
): Music {
    return Music(
        title = title,
        artist = artist,
        album = album,
        genre = genre,
        duration = duration,
    )
}