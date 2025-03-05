package com.exchange_rates_app.exchange_rate_api.Services;

import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ExchangeRateScheduler {

    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateScheduler(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    //@Scheduled(cron = "0 0 16 * * ?")
    public void fetchAndSaveRates() {
        try {
            String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
            exchangeRateService.saveCurrencyRates(url);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
