package com.example.bankid.infrastructure.frankfurter.impl

import com.example.bankid.api.exception.ExternalServiceException
import com.example.bankid.infrastructure.frankfurter.FrankfurterClient
import com.example.bankid.infrastructure.frankfurter.model.FrankfurterResponse
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class FrankfurterClientImpl(private val restTemplate: RestTemplate) : FrankfurterClient {

    override fun fetchRate(baseCurrency: String): FrankfurterResponse {
        try {
            val url = UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("base", baseCurrency)
                .build()
                .toUriString()

            return restTemplate.getForObject(url, FrankfurterResponse::class.java)
                ?: throw ExternalServiceException("Failed to fetch rates from Frankfurter API")
        } catch (e: Exception) {
            throw ExternalServiceException("Error fetching rates from Frankfurter API", e)
        }
    }

    companion object {
        private const val BASE_URL = "https://api.frankfurter.dev/v1/latest"
    }
}