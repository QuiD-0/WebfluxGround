package com.quid.webfluxground.notification.gateway.web

import com.quid.webfluxground.notification.usecase.FindHistory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/push/history")
class HistoryController(
    private val findHistory: FindHistory
) {

    @GetMapping("/receiver/{receiver}")
    fun byReceiver(@PathVariable receiver: String) = findHistory.byReceiver(receiver)

}