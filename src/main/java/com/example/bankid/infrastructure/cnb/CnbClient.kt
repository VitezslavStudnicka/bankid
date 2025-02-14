package com.example.bankid.infrastructure.cnb

import com.example.bankid.domain.exchangerates.model.ExchangeRate

interface CnbClient {
    fun fetchRates(): List<ExchangeRate>
}