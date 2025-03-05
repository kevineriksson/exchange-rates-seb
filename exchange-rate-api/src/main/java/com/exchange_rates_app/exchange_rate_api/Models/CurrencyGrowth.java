package com.exchange_rates_app.exchange_rate_api.Models;

public class CurrencyGrowth {
    private String currency;
    private Double growth;

    public CurrencyGrowth(String currency, Double growth) {
        this.currency = currency;
        this.growth = growth;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getGrowth() {
        return growth;
    }

    public void getGrowth(Double rate) {
        this.growth = rate;
    }
}

