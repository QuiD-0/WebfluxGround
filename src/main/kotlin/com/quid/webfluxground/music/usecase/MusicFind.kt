package com.quid.webfluxground.music.usecase

import com.quid.webfluxground.music.domain.Music
import com.quid.webfluxground.music.gateway.repository.MusicRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

interface MusicFind {

    fun all(): Flux<Music>

    @Service
    class MusicFindUseCase(
        private val musicRepository: MusicRepository
    ): MusicFind {
        override fun all(): Flux<Music> = musicRepository.findAll()
    }
}