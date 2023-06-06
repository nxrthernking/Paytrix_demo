package com.example.paytrix_demo.service;

import com.example.paytrix_demo.RequestData;
import com.example.paytrix_demo.client.CurrencyConversionClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

@Service
public class CurrencyConversionService {

    private final CurrencyConversionClient client;

    public CurrencyConversionService(CurrencyConversionClient client) {
        this.client = client;
    }

    public BigDecimal getExchangeRate(RequestData data) {
        String exchange = client.exchange(data.getSource(), data.getDestination());
        return  data.getAmount().divide(mapExchange(data, exchange), RoundingMode.FLOOR);

    }

    private BigDecimal mapExchange(RequestData data, String exchange) {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(exchange);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode gbpeurNode = rootNode.path("rates").path(data.getSource().toUpperCase(Locale.ROOT).concat(data.getDestination().toUpperCase()));

        String value = gbpeurNode.get(0).asText();
        return BigDecimal.valueOf(Double.parseDouble(value));
    }
}
