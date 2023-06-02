package com.quid.webfluxground.music.gateway.web

import com.quid.webfluxground.music.domain.Music
import com.quid.webfluxground.music.gateway.web.dto.MusicCreateRequest
import com.quid.webfluxground.music.gateway.web.dto.MusicUpdateRequest
import com.quid.webfluxground.music.usecase.MusicFind
import com.quid.webfluxground.music.usecase.MusicSave
import com.quid.webfluxground.music.usecase.MusicUpdate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/music")
class MusicController(
    private val musicFind: MusicFind,
    private val musicSave: MusicSave,
    private val musicUpdate: MusicUpdate,
) {

    @GetMapping("/list")
    fun getMusicList(): Flux<Music> = musicFind.all()

    @PostMapping
    fun createMusic(@RequestBody request: MusicCreateRequest): Mono<Music> {
        return musicSave.persist(request.toMusic())
    }

    @PutMapping
    fun updateMusic(@RequestBody request: MusicUpdateRequest): Mono<Music> {
        return musicUpdate.update(request)
    }
}