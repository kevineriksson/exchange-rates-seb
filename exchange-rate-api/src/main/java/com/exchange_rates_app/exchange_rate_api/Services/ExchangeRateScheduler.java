package com.exchange_rates_app.exchange_rate_api.Services;

import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class ExchangeRateScheduler {

    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateScheduler(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Scheduled(cron = "0 0 16 * * ?")
    public void fetchAndSaveRates() throws JAXBException, IOException {
        LocalDate lastDate = exchangeRateService.findByDate();
        LocalDate currentDate = LocalDate.now();

        if (lastDate == null || !lastDate.equals(currentDate)) {
            exchangeRateService.saveCurrencyRates("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml");
        }
    }
}
