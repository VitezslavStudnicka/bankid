package com.example.bankid.api.mapper

import com.example.bankid.api.dto.CurrencyPairDto
import com.example.bankid.api.dto.RateComparisonDto
import com.example.bankid.domain.exchangerates.model.CurrencyPair
import com.example.bankid.domain.exchangerates.model.RateComparison

object DtoMapper {
    fun CurrencyPair.toDto() = CurrencyPairDto(
        baseCurrency = baseCurrency,
        quoteCurrency = quoteCurrency
    )

    fun RateComparison.toDto() = RateComparisonDto(
        currencyPair = currencyPair.toDto(),
        cnbRate = cnbRate,
        externalRate = externalRate,
        difference = difference
    )
}