package com.example.bankid.api.dto

import java.math.BigDecimal

data class RateComparisonDto(
    val currencyPair: CurrencyPairDto,
    val cnbRate: BigDecimal,
    val externalRate: BigDecimal,
    val difference: BigDecimal
)