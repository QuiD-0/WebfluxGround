package com.quid.webfluxground.file.gateway.api

import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.io.File


@RestController
@RequestMapping("/api/file")
class FileController {

    @GetMapping
    fun download(exchange: ServerWebExchange): Mono<Void> {
        val dataBuffer = DefaultDataBufferFactory().wrap(File("C:\\Users\\quid\\Desktop\\test.jpg").readBytes())

        return exchange.response.apply {
            headers.apply {
                contentDisposition = ContentDisposition.parse("attachment; filename=test.jpg")
                contentType = MediaType.IMAGE_JPEG
                statusCode = HttpStatus.OK
            }
        }.writeWith(Flux.just(dataBuffer))
    }

    @PostMapping
    fun upload(exchange: ServerWebExchange): Mono<Void> {
        val filePartMono = exchange.multipartData
            .mapNotNull { it["file"]?.get(0) }
            .cast(FilePart::class.java)
        return filePartMono.flatMap { filePart ->
            val destinationFile = File("C:\\Users\\quid\\Desktop\\upload\\${filePart.filename()}")
            filePart.transferTo(destinationFile)
                .then(Mono.empty())
        }
    }
}
