// src/test/kotlin/com/example/bankid/domain/exchangerates/service/impl/ExchangeRateServiceImplTest.kt
package com.example.bankid.domain.exchangerates.service.impl

import com.example.bankid.api.exception.RateNotFoundException
import com.example.bankid.domain.exchangerates.model.CurrencyPair
import com.example.bankid.domain.exchangerates.model.ExchangeRate
import com.example.bankid.domain.exchangerates.service.CnbRateService
import com.example.bankid.domain.exchangerates.service.FrankfurterRateService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ExchangeRateServiceImplTest {

    private lateinit var cnbRateService: CnbRateService
    private lateinit var frankfurterRateService: FrankfurterRateService
    private lateinit var exchangeRateService: ExchangeRateServiceImpl

    @BeforeEach
    fun setup() {
        cnbRateService = mockk()
        frankfurterRateService = mockk()
        exchangeRateService = ExchangeRateServiceImpl(cnbRateService, frankfurterRateService)
    }

    @Test
    fun `getSupportedCurrencyPairs returns list of currency pairs with CZK base`() {
        val rates = listOf(
            ExchangeRate("USD", "dollar", "USA", 1, BigDecimal("23.456")),
            ExchangeRate("EUR", "euro", "EU", 1, BigDecimal("25.789"))
        )
        every { cnbRateService.fetchRates() } returns rates

        val pairs = exchangeRateService.getSupportedCurrencyPairs()

        assertEquals(2, pairs.size)
        assertTrue(pairs.all { it.baseCurrency == "CZK" })
        assertTrue(pairs.any { it.quoteCurrency == "USD" })
        assertTrue(pairs.any { it.quoteCurrency == "EUR" })
    }

    @Test
    fun `compareRates returns correct comparison`() {
        val baseCurrency = "USD"
        val cnbRate = ExchangeRate(
            currencyCode = baseCurrency,
            currencyName = "dollar",
            country = "USA",
            amount = 1,
            rate = BigDecimal("23.456")
        )
        val frankfurterRate = BigDecimal("23.123")

        every { cnbRateService.fetchRates() } returns listOf(cnbRate)
        every { frankfurterRateService.getRate(CurrencyPair(baseCurrency, "CZK")) } returns frankfurterRate

        val comparison = exchangeRateService.compareRates(baseCurrency)

        assertEquals(CurrencyPair(baseCurrency, "CZK"), comparison.currencyPair)
        assertEquals(cnbRate.rate, comparison.cnbRate)
        assertEquals(frankfurterRate, comparison.externalRate)
        assertEquals(
            cnbRate.rate.subtract(frankfurterRate),
            comparison.difference
        )
    }

    @Test
    fun `compareRates throws RateNotFoundException when currency not found`() {
        val nonExistentCurrency = "XXX"
        every { cnbRateService.fetchRates() } returns emptyList()

        assertThrows<RateNotFoundException> {
            exchangeRateService.compareRates(nonExistentCurrency)
        }
    }

    @Test
    fun `compareRates handles amount normalization correctly`() {
        val baseCurrency = "JPY"
        val cnbRate = ExchangeRate(
            currencyCode = baseCurrency,
            currencyName = "yen",
            country = "Japan",
            amount = 100,
            rate = BigDecimal("15.677")
        )
        val frankfurterRate = BigDecimal("0.15677")

        every { cnbRateService.fetchRates() } returns listOf(cnbRate)
        every { frankfurterRateService.getRate(CurrencyPair(baseCurrency, "CZK")) } returns frankfurterRate

        val comparison = exchangeRateService.compareRates(baseCurrency)

        assertEquals(
            BigDecimal("0.15677"),
            comparison.cnbRate
        )
        assertEquals(frankfurterRate, comparison.externalRate)
    }
}