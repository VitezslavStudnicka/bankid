package com.example.bankid.infrastructure.frankfurter

import com.example.bankid.infrastructure.frankfurter.model.FrankfurterResponse

interface FrankfurterClient {
    fun fetchRate(baseCurrency: String): FrankfurterResponse
}