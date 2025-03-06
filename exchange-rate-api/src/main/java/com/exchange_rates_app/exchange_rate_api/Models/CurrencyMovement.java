package com.exchange_rates_app.exchange_rate_api.Models;

public class CurrencyMovement {
    private final String currency;
    private final Double movement;

    public CurrencyMovement(String currency, Double growth) {
        this.currency = currency;
        this.movement = growth;
    }

    public Double getMovement() {
        return movement;
    }
}

