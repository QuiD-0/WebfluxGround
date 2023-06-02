package com.quid.webfluxground.music.gateway.web

import com.quid.webfluxground.music.gateway.web.dto.MusicCreateRequest
import com.quid.webfluxground.music.gateway.web.dto.MusicResponse
import com.quid.webfluxground.music.gateway.web.dto.MusicUpdateRequest
import com.quid.webfluxground.music.gateway.web.dto.toMusicResponse
import com.quid.webfluxground.music.usecase.MusicFind
import com.quid.webfluxground.music.usecase.MusicSave
import com.quid.webfluxground.music.usecase.MusicUpdate
import org.springframework.web.bind.annotation.*
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
    fun getMusicList(): Flux<MusicResponse> = musicFind.all().flatMap { Mono.just(toMusicResponse(it)) }

    @PostMapping
    fun createMusic(@RequestBody request: MusicCreateRequest): Mono<MusicResponse> {
        return musicSave.persist(request.toMusic()).map { toMusicResponse(it) }
    }

    @PutMapping
    fun updateMusic(@RequestBody request: MusicUpdateRequest): Mono<MusicResponse> {
        return musicUpdate.update(request).map { toMusicResponse(it) }
    }
}