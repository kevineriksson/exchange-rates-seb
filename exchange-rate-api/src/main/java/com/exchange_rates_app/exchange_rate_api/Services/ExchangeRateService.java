package com.exchange_rates_app.exchange_rate_api.Services;

import com.exchange_rates_app.exchange_rate_api.Models.*;
import com.exchange_rates_app.exchange_rate_api.Repos.ExchangeRateRepo;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExchangeRateService {
    private final ExchangeRateRepo exchangeRateRepo;

    public ExchangeRateService(ExchangeRateRepo exchangeRateRepo) {
        this.exchangeRateRepo = exchangeRateRepo;
    }

    public void saveCurrencyRates(String url) throws JAXBException, IOException {
        Envelope envelope = ExchangeRateFetcher.parseXML(url);
        CubeWrapper cubeWrapper = envelope.getCubeContainer().getCubeWrappers().get(0);
        LocalDate date = LocalDate.parse(envelope.getCubeContainer().getCubeWrappers().get(0).getTime());

        for (CubeRates rateData : cubeWrapper.getCubeRates()) {
            CurrencyRate currencyRate = new CurrencyRate();
            currencyRate.setCurrency(rateData.getCurrency());
            currencyRate.setRate(Double.valueOf(rateData.getRate()));
            currencyRate.setDate(date);

            exchangeRateRepo.save(currencyRate);
        }

    }

    public List<CurrencyRate> getAllCurrencyRates() {
        return exchangeRateRepo.findAll();
    }

    public CurrencyRate getCurrencyRateById(Long id) {
        Optional<CurrencyRate> currencyRate = exchangeRateRepo.findById(id);
        return currencyRate.orElse(null);
    }

    public List<CurrencyGrowth> get90DayCurrencyRates(String url) throws JAXBException, IOException {
        Envelope envelope = ExchangeRateFetcher.parseXML(url);

        Map<String, List<CurrencyRate>> currencyRates = new HashMap<>();
        for (CubeWrapper cubeWrapper : envelope.getCubeContainer().getCubeWrappers()) {
            for (CubeRates rateData : cubeWrapper.getCubeRates()) {
                currencyRates
                        .computeIfAbsent(rateData.getCurrency(), k -> new ArrayList<>());
            }
        }

        ExchangeRateCalculator exchangeRateCalculator = new ExchangeRateCalculator();
        List<CurrencyGrowth> growthList = exchangeRateCalculator.calculateGrowth(currencyRates);
        return getTop5CurrencyGrowth(growthList);
    }

    private List<CurrencyGrowth> getTop5CurrencyGrowth(List<CurrencyGrowth> growthList) {
        return growthList.stream()
                .sorted((a, b) -> Double.compare(b.getGrowth(), a.getGrowth()))
                .limit(5)
                .collect(Collectors.toList());
    }
}
