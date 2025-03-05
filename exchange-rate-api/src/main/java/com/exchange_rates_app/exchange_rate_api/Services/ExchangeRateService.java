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
    private String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml";

    public ExchangeRateService(ExchangeRateRepo exchangeRateRepo) {
        this.exchangeRateRepo = exchangeRateRepo;
    }

    public void saveCurrencyRates(String url) throws JAXBException, IOException {
        Envelope envelope = ExchangeRateFetcher.fetchExchangeRates(url);
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
    public List<CurrencyGrowth> getTop5CurrencyMovers() throws JAXBException, IOException {
        return findTop5CurrencyMovers(url);
    }

    public List<CurrencyGrowth> findTop5CurrencyMovers(String url) throws JAXBException, IOException {
        Envelope envelope = ExchangeRateFetcher.fetchExchangeRates(url);

        Map<String, List<CurrencyRate>> currencyRates = envelope.getCubeContainer().getCubeWrappers().stream()
                .flatMap(cubeWrapper -> cubeWrapper.getCubeRates().stream())
                .map(rateData -> {
                    CurrencyRate currencyRate = new CurrencyRate();
                    currencyRate.setRate(Double.parseDouble(rateData.getRate()));
                    currencyRate.setCurrency(rateData.getCurrency());
                    return currencyRate;
                })
                .collect(Collectors.groupingBy(CurrencyRate::getCurrency));

        ExchangeRateCalculator exchangeRateCalculator = new ExchangeRateCalculator();
        List<CurrencyGrowth> growthList = exchangeRateCalculator.calculateGrowth(currencyRates);
        return getTop5CurrenciesBasedOnGrowth(growthList);
    }

    private List<CurrencyGrowth> getTop5CurrenciesBasedOnGrowth(List<CurrencyGrowth> growthList) {
        return growthList.stream()
                .sorted((a, b) -> Double.compare(b.getGrowth(), a.getGrowth()))
                .limit(5)
                .collect(Collectors.toList());
    }
}
