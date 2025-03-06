package com.exchange_rates_app.exchange_rate_api.Controllers;

import com.exchange_rates_app.exchange_rate_api.Models.CurrencyGrowth;
import com.exchange_rates_app.exchange_rate_api.Models.CurrencyRate;
import com.exchange_rates_app.exchange_rate_api.Services.ExchangeRateScheduler;
import com.exchange_rates_app.exchange_rate_api.Services.ExchangeRateService;
import jakarta.xml.bind.JAXBException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.exchange_rates_app.exchange_rate_api.Services.ExchangeRateService.MovementType.BIGGEST_MOVEMENT;
import static com.exchange_rates_app.exchange_rate_api.Services.ExchangeRateService.MovementType.GROWTH;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/currencyRates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;
    private final ExchangeRateScheduler exchangeRateScheduler;

    @Autowired
    public ExchangeRateController(ExchangeRateService exchangeRateService, ExchangeRateScheduler exchangeRateScheduler) {
        this.exchangeRateService = exchangeRateService;
        this.exchangeRateScheduler = exchangeRateScheduler;
    }

    @GetMapping
    public List<CurrencyRate> getAllCurrencyRates() {
        return exchangeRateService.getAllCurrencyRates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyRate> getCurrencyById(@PathVariable Long id) {
        Optional<CurrencyRate> currencyRate = Optional.ofNullable(exchangeRateService.getCurrencyRateById(id));
        return currencyRate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/top5/growth")
    public List<CurrencyGrowth> getTop5GrowthCurrencyMovers() throws JAXBException, IOException {
        return exchangeRateService.getTop5CurrencyMovers(GROWTH);
    }

    @GetMapping("/top5/movement")
    public List<CurrencyGrowth> getTop5CurrencyMovers() throws JAXBException, IOException {
        return exchangeRateService.getTop5CurrencyMovers(BIGGEST_MOVEMENT);
    }
}
