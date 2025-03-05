package com.exchange_rates_app.exchange_rate_api.Models;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAttribute;

import java.time.LocalDate;

@Entity
@Table(name = "currencyRates")
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @XmlAttribute (name = "currency")
    private String currency;

    @XmlAttribute (name = "rate")
    @Column(nullable = false)
    private Double rate;

    @Column(nullable = false)
    private LocalDate date;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

