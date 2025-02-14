package com.example.bankid.domain.exchangerates.service.impl

import com.example.bankid.api.exception.RateNotFoundException
import com.example.bankid.domain.exchangerates.model.CurrencyPair
import com.example.bankid.domain.exchangerates.model.RateComparison
import com.example.bankid.domain.exchangerates.service.CnbRateService
import com.example.bankid.domain.exchangerates.service.ExchangeRateService
import com.example.bankid.domain.exchangerates.service.FrankfurterRateService
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ExchangeRateServiceImpl(
    private val cnbRateService: CnbRateService,
    private val frankfurterRateService: FrankfurterRateService,
) : ExchangeRateService {

    override fun getSupportedCurrencyPairs(): List<CurrencyPair> {
        return cnbRateService.fetchRates()
            .map { rate ->
                CurrencyPair(CURRENCY_CZK, rate.currencyCode)
            }
    }

    override fun compareRates(baseCurrency: String): RateComparison {
        val cnbRate = cnbRateService.fetchRates()
            .firstOrNull { it.currencyCode == baseCurrency }
            ?.let { normalizeRate(it.rate, it.amount) }
            ?: throw RateNotFoundException(baseCurrency)

        val currencyPair = CurrencyPair(baseCurrency, CURRENCY_CZK)
        val frankfurterRate = frankfurterRateService.getRate(currencyPair)

        val difference = cnbRate.subtract(frankfurterRate)

        return RateComparison(
            currencyPair = currencyPair,
            cnbRate = cnbRate,
            externalRate = frankfurterRate,
            difference = difference
        )
    }

    private fun normalizeRate(rate: BigDecimal, amount: Int): BigDecimal {
        return if (amount > 1) {
            rate.divide(BigDecimal(amount))
        } else {
            rate
        }
    }

    companion object {
        private const val CURRENCY_CZK = "CZK"
    }
}