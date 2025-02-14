package com.example.bankid.api.exception

sealed class ExchangeRateException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)

class RateNotFoundException(currencyCode: String) :
    ExchangeRateException("Exchange rate not found for currency: $currencyCode")

class ExternalServiceException(message: String, cause: Throwable? = null) :
    ExchangeRateException(message, cause)

class InvalidCurrencyPairException(message: String) :
    ExchangeRateException(message)