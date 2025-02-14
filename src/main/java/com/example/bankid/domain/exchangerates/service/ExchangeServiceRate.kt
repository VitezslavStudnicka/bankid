package com.example.bankid.domain.exchangerates.service

import com.example.bankid.domain.exchangerates.model.CurrencyPair
import com.example.bankid.domain.exchangerates.model.RateComparison

interface ExchangeRateService {
    fun getSupportedCurrencyPairs(): List<CurrencyPair>
    fun compareRates(baseCurrency: String): RateComparison
}