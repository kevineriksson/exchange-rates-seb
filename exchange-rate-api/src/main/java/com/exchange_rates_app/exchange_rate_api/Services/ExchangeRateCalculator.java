package com.exchange_rates_app.exchange_rate_api.Services;

import com.exchange_rates_app.exchange_rate_api.Models.CurrencyGrowth;
import com.exchange_rates_app.exchange_rate_api.Models.CurrencyRate;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ExchangeRateCalculator {

    public List<CurrencyGrowth> calculateGrowth(Map<String, List<CurrencyRate>> currencyRates) {
        List<CurrencyGrowth> growths = new ArrayList<>();

        for (Map.Entry<String, List<CurrencyRate>> entry : currencyRates.entrySet()) {
            String currency = entry.getKey();
            List<CurrencyRate> rateList = entry.getValue();

            if (rateList.size() >= 10) {
                Double initialRate = rateList.get(0).getRate();
                Double day10Rate = rateList.get(10).getRate();

                Double growth = ((initialRate - day10Rate) / day10Rate) * 100;
                growths.add(new CurrencyGrowth(currency, growth));
            }
        }
        return growths;
    }

    public List<CurrencyGrowth> calculateTopMovements(Map<String, List<CurrencyRate>> currencyRates) {
        List<CurrencyGrowth> movements = new ArrayList<>();

        for (Map.Entry<String, List<CurrencyRate>> entry : currencyRates.entrySet()) {
            String currency = entry.getKey();
            List<CurrencyRate> rateList = entry.getValue();

            double maxGrowth = Double.NEGATIVE_INFINITY;
            double maxDecline = Double.POSITIVE_INFINITY;

            for (int i = 0; i <= rateList.size() - 10; i++) {
                double startRate = rateList.get(i).getRate();
                double endRate = rateList.get(i + 10).getRate();
                double change = ((endRate - startRate) / startRate) * 100;

                maxGrowth = Math.max(maxGrowth, change);
                maxDecline = Math.min(maxDecline, change);
            }

            movements.add(new CurrencyGrowth(currency, maxGrowth));
            movements.add(new CurrencyGrowth(currency, maxDecline));
        }
        return movements;
    }
}
