package com.quid.webfluxground.music.usecase

import com.quid.webfluxground.music.domain.Music
import com.quid.webfluxground.music.gateway.repository.MusicRepository
import com.quid.webfluxground.music.gateway.web.dto.MusicUpdateRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

interface MusicUpdate {
    fun update(request: MusicUpdateRequest): Mono<Music>

    @Service
    class MusicUpdateUseCase(
        private val musicRepository: MusicRepository,
    ) : MusicUpdate {
        override fun update(request: MusicUpdateRequest): Mono<Music> = musicRepository.findById(request.id)
            .map { it.copy(title = request.title, artist = request.artist) }
            .flatMap { musicRepository.save(it) }

    }
}