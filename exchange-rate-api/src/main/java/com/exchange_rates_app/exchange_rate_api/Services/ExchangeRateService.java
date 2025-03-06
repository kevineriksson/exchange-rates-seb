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
    public List<CurrencyGrowth> getTop5CurrencyMovers(Enum movementType) throws JAXBException, IOException {
        String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml";

        if (movementType == MovementType.GROWTH){
            return findTop5GrowthCurrencyMovers(url);
        } else if (movementType == MovementType.BIGGEST_MOVEMENT) {
            return findTop5CurrencyMovers(url);
        }
        return List.of();
    }

    public List<CurrencyGrowth> findTop5GrowthCurrencyMovers(String url) throws JAXBException, IOException {
        Map<String, List<CurrencyRate>> currencyRates = getStringListMap(url);

        ExchangeRateCalculator exchangeRateCalculator = new ExchangeRateCalculator();
        List<CurrencyGrowth> growthList = exchangeRateCalculator.calculateGrowth(currencyRates);
        return getTop5CurrenciesBasedOnGrowth(growthList);
    }

    public List<CurrencyGrowth> findTop5CurrencyMovers(String url) throws JAXBException, IOException {
        Map<String, List<CurrencyRate>> currencyRates = getStringListMap(url);

        ExchangeRateCalculator exchangeRateCalculator = new ExchangeRateCalculator();
        List<CurrencyGrowth> moversList = exchangeRateCalculator.calculateTopMovements(currencyRates);
        return getTop5CurrenciesBasedOnGrowth(moversList);
    }
    private List<CurrencyGrowth> getTop5CurrenciesBasedOnGrowth(List<CurrencyGrowth> growthList) {
        return growthList.stream()
                .sorted((a, b) -> Double.compare(Math.abs(b.getGrowth()), Math.abs(a.getGrowth())))
                .limit(5)
                .collect(Collectors.toList());
    }
    private static Map<String, List<CurrencyRate>> getStringListMap(String url) throws JAXBException, IOException {
        Envelope envelope = ExchangeRateFetcher.fetchExchangeRates(url);

        return envelope.getCubeContainer().getCubeWrappers().stream()
                .flatMap(cubeWrapper -> cubeWrapper.getCubeRates().stream())
                .map(rateData -> {
                    CurrencyRate currencyRate = new CurrencyRate();
                    currencyRate.setRate(Double.parseDouble(rateData.getRate()));
                    currencyRate.setCurrency(rateData.getCurrency());
                    return currencyRate;
                })
                .collect(Collectors.groupingBy(CurrencyRate::getCurrency));
    }

    public enum MovementType {
        GROWTH,
        BIGGEST_MOVEMENT
    }
}

