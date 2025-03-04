package com.exchange_rates_app.exchange_rate_api.Controllers;

import com.exchange_rates_app.exchange_rate_api.Models.CurrencyRate;
import com.exchange_rates_app.exchange_rate_api.Services.ExchangeRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public class ExchangeRateController {

    private ExchangeRateService exchangeRateService;

    @GetMapping
    public List<CurrencyRate> getAllCurrencyRates() {
        return exchangeRateService.getAllCurrencyRates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyRate> getCurrencyById(@PathVariable Long id) {
        Optional<CurrencyRate> currencyRate = Optional.ofNullable(exchangeRateService.getCurrencyRateById(id));
        return currencyRate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
