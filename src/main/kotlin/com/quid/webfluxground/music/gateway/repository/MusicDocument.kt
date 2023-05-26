package com.quid.webfluxground.music.gateway.repository

import com.quid.webfluxground.music.domain.Music

class MusicDocument {
    fun toDomain(): Music {
        return Music()
    }
}