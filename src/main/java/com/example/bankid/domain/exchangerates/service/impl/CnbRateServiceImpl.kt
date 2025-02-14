package com.example.bankid.domain.exchangerates.service.impl

import com.example.bankid.api.exception.ExternalServiceException
import com.example.bankid.api.exception.RateNotFoundException
import com.example.bankid.domain.exchangerates.model.ExchangeRate
import com.example.bankid.domain.exchangerates.service.CnbRateService
import com.example.bankid.infrastructure.cnb.impl.CnbClientImpl
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CnbRateServiceImpl(
    private val cnbClient: CnbClientImpl
) : CnbRateService {

    override fun fetchRates(): List<ExchangeRate> {
        try {
            return cnbClient.fetchRates()
        } catch (e: Exception) {
            throw ExternalServiceException("Failed to fetch CNB rates", e)
        }
    }

    override fun fetchRate(currencyCode: String): BigDecimal {
        return fetchRates()
            .firstOrNull { it.currencyCode == currencyCode }
            ?.rate
            ?: throw RateNotFoundException(currencyCode)
    }
}