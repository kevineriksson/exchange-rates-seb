package com.exchange_rates_app.exchange_rate_api.Services;

import com.exchange_rates_app.exchange_rate_api.Models.CurrencyGrowth;
import com.exchange_rates_app.exchange_rate_api.Models.CurrencyRate;
import com.exchange_rates_app.exchange_rate_api.Repos.ExchangeRateRepo;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExchangeRateCalculator {

    public List<CurrencyGrowth> calculateGrowth(Map<String, List<CurrencyRate>> currencyRates) throws JAXBException, IOException {
        List<CurrencyGrowth> growthList = new ArrayList<>();

        for (Map.Entry<String, List<CurrencyRate>> entry : currencyRates.entrySet()) {
            String currency = entry.getKey();
            List<CurrencyRate> rateList = entry.getValue();

            if (rateList.size() >= 10) {
                Double initialRate = rateList.get(0).getRate();
                Double day10Rate = rateList.get(10).getRate();

                Double growth = ((initialRate - day10Rate) / day10Rate) * 100;
                growthList.add(new CurrencyGrowth(currency, growth));
            }
        }
        return growthList;
    }
}
