package com.example.demo.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/public")
class PublicController {

    @GetMapping("/health")
    fun health() =
        mapOf("status" to "OK", "timestamp" to LocalDateTime.now())
}
