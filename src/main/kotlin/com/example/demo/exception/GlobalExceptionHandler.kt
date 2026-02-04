package com.example.demo.exception

import org.apache.coyote.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


class NotFoundException(message: String) : RuntimeException(message)
class BadRequestException(message: String) : RuntimeException(message)
class UnauthorizedActionException(message: String) : RuntimeException(message)


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: NotFoundException) =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(mapOf("error" to ex.message))

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(ex: BadRequestException) =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(mapOf("error" to ex.message))

    @ExceptionHandler(UnauthorizedActionException::class)
    fun handleUnauthorized(ex: UnauthorizedActionException) =
        ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(mapOf("error" to ex.message))
}
