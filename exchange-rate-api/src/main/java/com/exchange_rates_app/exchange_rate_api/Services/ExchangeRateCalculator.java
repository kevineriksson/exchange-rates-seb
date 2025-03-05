package com.exchange_rates_app.exchange_rate_api.Services;

import com.exchange_rates_app.exchange_rate_api.Models.CurrencyGrowth;
import com.exchange_rates_app.exchange_rate_api.Models.CurrencyRate;
import java.util.*;

public class ExchangeRateCalculator {

    public List<CurrencyGrowth> calculateGrowth(Map<String, List<CurrencyRate>> currencyRates) {
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
    /*
    public List<CurrencyGrowth> calculateBestDeclineMovement(Map<String, List<CurrencyRate>> currencyRates) throws JAXBException, IOException {
        Map<String, Double> worstDecline = new HashMap<>();

        for (Map.Entry<String, List<CurrencyRate>> entry : currencyRates.entrySet()) {
            String currency = entry.getKey();
            List<CurrencyRate> rateList = entry.getValue();

            double maxGrowth = Double.NEGATIVE_INFINITY;
            double maxDecline = Double.POSITIVE_INFINITY;

            for (int i = 0; i <= rateList.size() - 10; i++) {
                double startRate = rateList.get(i).getRate();
                double endRate = rateList.get(i + 11).getRate();
                double change = ((endRate - startRate) / startRate) * 100;

                maxGrowth = Math.max(maxGrowth, change);
                maxDecline = Math.min(maxDecline, change);
            }
        }
    }
    public List<CurrencyGrowth>  calculateBestGrowthMovement(Map<String, List<CurrencyRate>> currencyRates) throws JAXBException, IOException {
        Map<String, Double> bestGrowth = new HashMap<>();

        for (Map.Entry<String, List<CurrencyRate>> entry : currencyRates.entrySet()) {
            String currency = entry.getKey();
            List<CurrencyRate> rateList = entry.getValue();

            double maxGrowth = Double.NEGATIVE_INFINITY;
            double maxDecline = Double.POSITIVE_INFINITY;

            for (int i = 0; i <= rateList.size() - 10; i++) {
                double startRate = rateList.get(i).getRate();
                double endRate = rateList.get(i + 11).getRate();
                double change = ((endRate - startRate) / startRate) * 100;

                maxGrowth = Math.max(maxGrowth, change);
                maxDecline = Math.min(maxDecline, change);
            }
        }
    }*/
}
