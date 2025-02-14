package com.example.bankid.domain.exchangerates.service

import com.example.bankid.domain.exchangerates.model.ExchangeRate
import java.math.BigDecimal

interface CnbRateService {
    fun fetchRates(): List<ExchangeRate>
    fun fetchRate(currencyCode: String): BigDecimal
}