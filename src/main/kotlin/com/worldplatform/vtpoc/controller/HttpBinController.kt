package com.worldplatform.vtpoc.controller

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient


@RestController
@RequestMapping("/httpbin")
class HttpBinController {
    private val log = KotlinLogging.logger { }

    private val restClient: RestClient = RestClient.builder().baseUrl("https://httpbin.org").build()

    @GetMapping("/block/{seconds}")
    fun delay(@PathVariable seconds: Int): String {
        val result = restClient.get()
            .uri("/delay/$seconds")
            .retrieve()
            .toBodilessEntity()

        log.info("{} on {}", result.statusCode, Thread.currentThread())

        return Thread.currentThread().toString()
    }
}