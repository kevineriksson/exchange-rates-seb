package com.exchange_rates_app.exchange_rate_api.Controllers;

import com.exchange_rates_app.exchange_rate_api.Models.CurrencyMovement;
import com.exchange_rates_app.exchange_rate_api.Models.CurrencyRate;
import com.exchange_rates_app.exchange_rate_api.Services.ExchangeRateScheduler;
import com.exchange_rates_app.exchange_rate_api.Services.ExchangeRateService;
import jakarta.xml.bind.JAXBException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.util.List;
import static com.exchange_rates_app.exchange_rate_api.Services.ExchangeRateService.MovementType.BIGGEST_MOVEMENT;
import static com.exchange_rates_app.exchange_rate_api.Services.ExchangeRateService.MovementType.GROWTH;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/currencyRates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping
    public List<CurrencyRate> getAllCurrencyRates() {
        return exchangeRateService.getLastCurrencyRates();
    }


    @GetMapping("/top5/growth")
    public List<CurrencyMovement> getTop5GrowthCurrencyMovers() throws JAXBException, IOException {
        return exchangeRateService.getTop5CurrencyMovers(GROWTH);
    }

    @GetMapping("/top5/movement")
    public List<CurrencyMovement> getTop5CurrencyMovers() throws JAXBException, IOException {
        return exchangeRateService.getTop5CurrencyMovers(BIGGEST_MOVEMENT);
    }
}
