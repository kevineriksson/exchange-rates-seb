package com.exchange_rates_app.exchange_rate_api.Services;

import jakarta.xml.bind.JAXBException;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

public class ExchangeRateScheduler {
    private ExchangeRateService currencyRateService;

    private String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";

    @Scheduled(cron = "0 0 16 * * ?")
    public void fetchAndSaveRates() {
        try {
            currencyRateService.saveCurrencyRates(url);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
