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
    public Double getMovement() {
        return movement;
    }
}

