package com.exchange_rates_app.exchange_rate_api.Models;

public class CurrencyMovement {
    private String currency;
    private Double movement;

    public CurrencyMovement(String currency, Double growth) {
        this.currency = currency;
        this.movement = growth;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getMovement() {
        return movement;
    }

    public void getGrowth(Double rate) {
        this.movement = rate;
    }
}

