package com.example.paytrix_demo.controller;

import com.example.paytrix_demo.RequestData;
import com.example.paytrix_demo.service.CurrencyConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency")
public class CurrencyConversionController {

    private final static String RESPONSE_MESSAGE = "To buy %s of %s you will need %s of %s";

    private final CurrencyConversionService service;

    public CurrencyConversionController(CurrencyConversionService service) {
        this.service = service;
    }

    @GetMapping
    public String calculateCurrencyConversion(RequestData data) {
        BigDecimal exchangeRate = service.getExchangeRate(data);
        return String.format(RESPONSE_MESSAGE, data.getAmount(), data.getDestination(), exchangeRate,data.getSource());
    }
}
