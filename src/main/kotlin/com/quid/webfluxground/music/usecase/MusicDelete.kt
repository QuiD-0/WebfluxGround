package com.quid.webfluxground.music.usecase

import com.quid.webfluxground.music.gateway.repository.MusicRepository
import org.springframework.stereotype.Service

interface MusicDelete {
    fun delete(id: String)
    fun deleteBy(title: String, artist: String)

    @Service
    class MusicDeleteUseCase(
        private val musicRepository: MusicRepository,
    ) : MusicDelete {
        override fun delete(id: String) = musicRepository.delete(id)
        override fun deleteBy(title: String, artist: String) {
            musicRepository.deleteBy(title, artist)
        }
    }
}