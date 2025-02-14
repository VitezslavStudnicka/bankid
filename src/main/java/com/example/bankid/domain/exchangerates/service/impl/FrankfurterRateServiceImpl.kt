package com.example.bankid.domain.exchangerates.service.impl

import com.example.bankid.api.exception.RateNotFoundException
import com.example.bankid.domain.exchangerates.model.CurrencyPair
import com.example.bankid.domain.exchangerates.service.FrankfurterRateService
import com.example.bankid.infrastructure.frankfurter.impl.FrankfurterClientImpl
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class FrankfurterRateServiceImpl(
    private val frankfurterClient: FrankfurterClientImpl
) : FrankfurterRateService {

    override fun getRate(currencyPair: CurrencyPair): BigDecimal {
        val response = frankfurterClient.fetchRate(currencyPair.baseCurrency)
        return response.rates[currencyPair.quoteCurrency]
            ?: throw RateNotFoundException("Rate not found for ${currencyPair.quoteCurrency}")
    }
}