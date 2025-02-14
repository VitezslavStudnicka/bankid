package com.example.bankid.api.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(RateNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleRateNotFoundException(
        ex: RateNotFoundException,
        request: HttpServletRequest
    ): ErrorResponse = ErrorResponse(
        status = HttpStatus.NOT_FOUND.value(),
        error = "Not Found",
        message = ex.message ?: "Rate not found",
        path = request.requestURI
    )

    @ExceptionHandler(ExternalServiceException::class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    fun handleExternalServiceException(
        ex: ExternalServiceException,
        request: HttpServletRequest
    ): ErrorResponse = ErrorResponse(
        status = HttpStatus.SERVICE_UNAVAILABLE.value(),
        error = "Service Unavailable",
        message = ex.message ?: "External service error",
        path = request.requestURI
    )

    @ExceptionHandler(InvalidCurrencyPairException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleInvalidCurrencyPairException(
        ex: InvalidCurrencyPairException,
        request: HttpServletRequest
    ): ErrorResponse = ErrorResponse(
        status = HttpStatus.BAD_REQUEST.value(),
        error = "Bad Request",
        message = ex.message ?: "Invalid currency pair",
        path = request.requestURI
    )

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGenericException(
        ex: Exception,
        request: HttpServletRequest
    ): ErrorResponse = ErrorResponse(
        status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
        error = "Internal Server Error",
        message = "An unexpected error occurred",
        path = request.requestURI
    )
}