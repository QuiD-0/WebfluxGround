package com.quid.webfluxground.music.usecase

import com.quid.webfluxground.music.domain.Music
import com.quid.webfluxground.music.gateway.repository.MusicRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

interface MusicSave {

    fun persist(music: Music): Mono<Music>

    @Service
    class MusicSaveUseCase(
        private val musicRepository: MusicRepository,
    ) : MusicSave {
        override fun persist(music: Music): Mono<Music> =
            musicRepository.checkDuplicate(music)
                .filter(Boolean::not)
                .switchIfEmpty(Mono.error(IllegalArgumentException("Music already exists")))
                .then(musicRepository.save(music))

    }
}