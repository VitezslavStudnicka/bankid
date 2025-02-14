package com.example.bankid.infrastructure.cnb.mapper

import com.example.bankid.domain.exchangerates.model.ExchangeRate
import com.example.bankid.infrastructure.cnb.model.Radek
import java.math.BigDecimal
import java.math.RoundingMode

object CnbMapper {
    fun Radek.toDomain() = ExchangeRate(
        currencyCode = kod,
        currencyName = mena,
        country = zeme,
        amount = mnozstvi,
        rate = BigDecimal(kurz.replace(",", ".")).setScale(3, RoundingMode.HALF_UP)
    )
}