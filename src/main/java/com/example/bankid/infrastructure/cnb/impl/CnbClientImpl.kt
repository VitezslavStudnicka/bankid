package com.example.bankid.infrastructure.cnb.impl

import com.example.bankid.domain.exchangerates.model.ExchangeRate
import com.example.bankid.infrastructure.cnb.CnbClient
import com.example.bankid.infrastructure.cnb.mapper.CnbMapper.toDomain
import com.example.bankid.infrastructure.cnb.model.CnbResponse
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class CnbClientImpl(private val restTemplate: RestTemplate) : CnbClient {
    override fun fetchRates(): List<ExchangeRate> {
        val response = restTemplate.getForObject(CNB_URL, CnbResponse::class.java)
        return response?.tabulka?.radky?.map { it.toDomain() } ?: emptyList()
    }

    companion object {
        private const val CNB_URL =
            "https://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/denni_kurz.xml"
    }
}

