package com.exchange_rates_app.exchange_rate_api.Models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)

public class CubeRates {

    @XmlAttribute(name = "currency")
    private String currency;

    @XmlAttribute(name = "rate")
    private String rate;

    public String getCurrency() {
        return currency;
    }
    public String getRate() {
        return rate;
    }
}
