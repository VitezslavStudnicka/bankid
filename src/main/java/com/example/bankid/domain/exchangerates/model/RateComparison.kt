package com.example.bankid.domain.exchangerates.model

import java.math.BigDecimal

data class RateComparison(
    val currencyPair: CurrencyPair,
    val cnbRate: BigDecimal,
    val externalRate: BigDecimal,
    val difference: BigDecimal
)