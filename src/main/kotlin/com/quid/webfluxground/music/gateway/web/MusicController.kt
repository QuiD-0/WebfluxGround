package com.quid.webfluxground.music.gateway.web

import com.quid.webfluxground.music.gateway.web.dto.*
import com.quid.webfluxground.music.usecase.MusicDelete
import com.quid.webfluxground.music.usecase.MusicFind
import com.quid.webfluxground.music.usecase.MusicSave
import com.quid.webfluxground.music.usecase.MusicUpdate
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/music")
class MusicController(
    private val musicFind: MusicFind,
    private val musicSave: MusicSave,
    private val musicUpdate: MusicUpdate,
    private val musicDelete: MusicDelete,
) {

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    fun getMusicList(): Flux<MusicResponse> = musicFind.all().flatMap { Mono.just(toMusicResponse(it)) }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createMusic(@RequestBody request: MusicCreateRequest): Mono<MusicResponse> {
        return musicSave.persist(request.toMusic()).map { toMusicResponse(it) }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateMusic(@RequestBody request: MusicUpdateRequest): Mono<MusicResponse> {
        return musicUpdate.update(request).map { toMusicResponse(it) }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMusic(@RequestBody request: MusicDeleteRequest) {
        musicDelete.delete(request.id)
    }
}