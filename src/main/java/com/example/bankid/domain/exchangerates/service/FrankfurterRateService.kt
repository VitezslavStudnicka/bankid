package com.example.bankid.domain.exchangerates.service

import com.example.bankid.domain.exchangerates.model.CurrencyPair
import java.math.BigDecimal

interface FrankfurterRateService {
    fun getRate(currencyPair: CurrencyPair): BigDecimal
}