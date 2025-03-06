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
        List<CubeWrapper> cubeWrapperList = envelope.getCubeContainer().getCubeWrappers();
        List<CurrencyRate> currencyRates = new ArrayList<>();

        for (CubeWrapper cubeWrapper : cubeWrapperList) {
            LocalDate date = LocalDate.parse(cubeWrapper.getTime());
            for (CubeRates rateData : cubeWrapper.getCubeRates()) {
                CurrencyRate currencyRate = new CurrencyRate();
                currencyRate.setCurrency(rateData.getCurrency());
                currencyRate.setRate(Double.valueOf(rateData.getRate()));
                currencyRate.setDate(date);
                currencyRates.add(currencyRate);
            }
        }
        exchangeRateRepo.saveAll(currencyRates);
    }

    public LocalDate findByDate() {
        return exchangeRateRepo.findLatestDate();
    }
    public List<CurrencyRate> getLastCurrencyRates() {
        return exchangeRateRepo.findByDate(findByDate());
    }

    public List<CurrencyMovement> getTop5CurrencyMovers(Enum movementType) throws JAXBException, IOException {
        String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml";

        if (movementType == MovementType.GROWTH){
            return findTop5GrowthCurrencyMovers(url);
        } else if (movementType == MovementType.BIGGEST_MOVEMENT) {
            return findTop5CurrencyMovers(url);
        }
        return List.of();
    }

    public List<CurrencyMovement> findTop5GrowthCurrencyMovers(String url) throws JAXBException, IOException {
        Map<String, List<CurrencyRate>> currencyRates = getStringListMap(url);

        ExchangeRateCalculator exchangeRateCalculator = new ExchangeRateCalculator();
        List<CurrencyMovement> growthList = exchangeRateCalculator.calculateGrowth(currencyRates);
        return getTop5CurrenciesBasedOnGrowth(growthList);
    }

    public List<CurrencyMovement> findTop5CurrencyMovers(String url) throws JAXBException, IOException {
        Map<String, List<CurrencyRate>> currencyRates = getStringListMap(url);

        ExchangeRateCalculator exchangeRateCalculator = new ExchangeRateCalculator();
        List<CurrencyMovement> moversList = exchangeRateCalculator.calculateTopMovements(currencyRates);
        return getTop5CurrenciesBasedOnGrowth(moversList);
    }
    private List<CurrencyMovement> getTop5CurrenciesBasedOnGrowth(List<CurrencyMovement> growthList) {
        return growthList.stream()
                .sorted((a, b) -> Double.compare(Math.abs(b.getMovement()), Math.abs(a.getMovement())))
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

