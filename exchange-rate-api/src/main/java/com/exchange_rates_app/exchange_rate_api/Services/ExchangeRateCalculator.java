package com.exchange_rates_app.exchange_rate_api.Services;

import com.exchange_rates_app.exchange_rate_api.Models.CurrencyMovement;
import com.exchange_rates_app.exchange_rate_api.Models.CurrencyRate;

import java.util.*;

public class ExchangeRateCalculator {

    public List<CurrencyMovement> calculateGrowth(Map<String, List<CurrencyRate>> currencyRates) {
        List<CurrencyMovement> growths = new ArrayList<>();

        for (Map.Entry<String, List<CurrencyRate>> entry : currencyRates.entrySet()) {
            String currency = entry.getKey();
            List<CurrencyRate> rateList = entry.getValue();

            if (rateList.size() >= 10) {
                Double initialRate = rateList.get(0).getRate();
                Double day10Rate = rateList.get(10).getRate();

                Double growth = ((initialRate - day10Rate) / day10Rate) * 100;
                growths.add(new CurrencyMovement(currency, growth));
            }
        }
        return growths;
    }

    public List<CurrencyMovement> calculateTopMovements(Map<String, List<CurrencyRate>> currencyRates) {
        List<CurrencyMovement> movements = new ArrayList<>();

        for (Map.Entry<String, List<CurrencyRate>> entry : currencyRates.entrySet()) {
            String currency = entry.getKey();
            List<CurrencyRate> rateList = entry.getValue();

            double maxGrowth = Double.NEGATIVE_INFINITY;
            double maxDecline = Double.POSITIVE_INFINITY;

            for (int i = 0; i <= rateList.size() - 11; i++) {
                double startRate = rateList.get(i).getRate();
                double endRate = rateList.get(i + 10).getRate();
                double change = ((endRate - startRate) / startRate) * 100;

                maxGrowth = Math.max(maxGrowth, change);
                maxDecline = Math.min(maxDecline, change);
            }

            movements.add(new CurrencyMovement(currency, maxGrowth));
            movements.add(new CurrencyMovement(currency, maxDecline));
        }
        return movements;
    }
}
