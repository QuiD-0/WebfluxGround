package com.quid.webfluxground.music.gateway.web

import com.quid.webfluxground.music.domain.Music
import com.quid.webfluxground.music.usecase.MusicFind
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/music")
class MusicController(private val musicFind: MusicFind) {

    @GetMapping("/list")
    fun getMusicList(): Flux<Music> = musicFind.all()
}