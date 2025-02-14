package com.example.bankid.domain.exchangerates.model

import java.math.BigDecimal

data class ExchangeRate(
    val currencyCode: String,
    val currencyName: String,
    val country: String,
    val amount: Int,
    val rate: BigDecimal
)