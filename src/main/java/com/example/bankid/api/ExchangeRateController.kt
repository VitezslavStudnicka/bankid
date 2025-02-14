package com.example.bankid.api

import com.example.bankid.api.dto.CurrencyPairDto
import com.example.bankid.api.dto.RateComparisonDto
import com.example.bankid.api.mapper.DtoMapper.toDto
import com.example.bankid.domain.exchangerates.service.ExchangeRateService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/exchange-rates")
class ExchangeRateController(
    private val exchangeRateService: ExchangeRateService
) {

    @GetMapping("/pairs")
    fun getSupportedPairs(): List<CurrencyPairDto> =
        exchangeRateService.getSupportedCurrencyPairs()
            .map { it.toDto() }

    @GetMapping("/compare")
    fun compareRates(
        @RequestParam base: String
    ): RateComparisonDto {
        return exchangeRateService.compareRates(base).toDto()
    }
}