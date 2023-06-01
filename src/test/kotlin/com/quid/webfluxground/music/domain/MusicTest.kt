package com.quid.webfluxground.music.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MusicTest {

    @Test
    fun copyTest() {
        val music = createMusic(
            "title",
            "artist",
            "album",
            "genre",
            213,
        )
        val musicCopy = music.copy(title = "title2")

        assertEquals("title", music.title)
        assertEquals("title2", musicCopy.title)
    }
}