package com.example.bankid.infrastructure.frankfurter.model

import java.math.BigDecimal
import java.time.LocalDate

data class FrankfurterResponse(
    val amount: BigDecimal,
    val base: String,
    val date: LocalDate,
    val rates: Map<String, BigDecimal>
)