package com.quid.webfluxground.music.usecase

import com.quid.webfluxground.music.gateway.web.dto.MusicCreateRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MusicDeleteTest{

    @Autowired lateinit var musicDelete: MusicDelete
    @Autowired lateinit var musicSave: MusicSave

    @Test
    fun `delete music`(){
        val music = musicSave.persist(
            MusicCreateRequest(
                title = "titleTest",
                artist = "artistTest",
                album = "album",
                genre = "genre",
                duration = 100
            ).toMusic()
        ).block()

        assertDoesNotThrow { musicDelete.delete(music!!.id!!) }
    }
}