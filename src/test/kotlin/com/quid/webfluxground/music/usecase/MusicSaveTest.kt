package com.quid.webfluxground.music.usecase

import com.quid.webfluxground.music.gateway.web.dto.MusicCreateRequest
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MusicSaveTest {

    @Autowired
    lateinit var musicSave: MusicSave
    @Autowired
    lateinit var musicDelete: MusicDelete

    @AfterEach
    fun tearDown() {
        musicDelete.deleteBy("titleTest","artistTest")
    }

    @Test
    fun `save music`() {
        assertDoesNotThrow {
            musicSave.persist(
                MusicCreateRequest(
                    title = "titleTest",
                    artist = "artistTest",
                    album = "album",
                    genre = "genre",
                    duration = 100
                ).toMusic()
            ).block()
        }
    }

    @Test
    fun `duplicate save fail`(){
        val request = MusicCreateRequest(
            title = "titleTest",
            artist = "artistTest",
            album = "album",
            genre = "genre",
            duration = 100
        ).toMusic()

        musicSave.persist(request).block()

        assertThrows<Exception> { musicSave.persist(request).block() }
    }
}