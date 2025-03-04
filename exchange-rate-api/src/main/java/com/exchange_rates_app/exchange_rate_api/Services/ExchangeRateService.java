package com.exchange_rates_app.exchange_rate_api.Services;

import com.exchange_rates_app.exchange_rate_api.Models.Cube;
import com.exchange_rates_app.exchange_rate_api.Models.CubeRates;
import com.exchange_rates_app.exchange_rate_api.Models.CurrencyRate;
import com.exchange_rates_app.exchange_rate_api.Models.Envelope;
import com.exchange_rates_app.exchange_rate_api.Repos.ExchangeRateRepo;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.time.LocalDate;

public class ExchangeRateService {
    private final ExchangeRateRepo exchangeRateRepo;

    public ExchangeRateService(ExchangeRateRepo exchangeRateRepo) {
        this.exchangeRateRepo = exchangeRateRepo;
    }

    public void saveCurrencyRates(String url) throws JAXBException, IOException {
        Envelope envelope = ExchangeRateFetcher.parseXML(url);

        for (Cube cube : envelope.getCube()) {
            LocalDate date = LocalDate.parse(cube.getTime());

            for (CubeRates rateData : cube.getCubeRates()) {
                CurrencyRate currencyRate = new CurrencyRate();
                currencyRate.setCurrency(rateData.getCurrency());
                currencyRate.setRate(rateData.getRate());
                currencyRate.setDate(date);

                exchangeRateRepo.save(currencyRate);
            }
        }
    }


}
