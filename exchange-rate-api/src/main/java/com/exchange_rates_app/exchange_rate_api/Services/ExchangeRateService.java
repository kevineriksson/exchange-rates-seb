package com.exchange_rates_app.exchange_rate_api.Services;

import com.exchange_rates_app.exchange_rate_api.Models.*;
import com.exchange_rates_app.exchange_rate_api.Repos.ExchangeRateRepo;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRateService {
    private final ExchangeRateRepo exchangeRateRepo;

    public ExchangeRateService(ExchangeRateRepo exchangeRateRepo) {
        this.exchangeRateRepo = exchangeRateRepo;
    }

    public void saveCurrencyRates(String url) throws JAXBException, IOException {
        Envelope envelope = ExchangeRateFetcher.parseXML(url);

        for (CubeWrapper cubeWrapper : envelope.getCubeContainer().getCubeWrappers()) {
            LocalDate date = LocalDate.parse(cubeWrapper.getTime());

            for (CubeRates rateData : cubeWrapper.getCubeRates()) {
                CurrencyRate currencyRate = new CurrencyRate();
                currencyRate.setCurrency(rateData.getCurrency());
                currencyRate.setRate(Double.valueOf(rateData.getRate()));
                currencyRate.setDate(date);

                exchangeRateRepo.save(currencyRate);
            }
        }
    }

    public List<CurrencyRate> getAllCurrencyRates() {
        return exchangeRateRepo.findAll();
    }

    public CurrencyRate getCurrencyRateById(Long id) {
        Optional<CurrencyRate> currencyRate = exchangeRateRepo.findById(id);
        return currencyRate.orElse(null);
    }
}
