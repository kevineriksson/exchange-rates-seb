package com.exchange_rates_app.exchange_rate_api.Models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class CubeWrapper {

    @XmlAttribute(name = "time")
    private String time;

    private List<CubeRates> cubeRates;

    @XmlElement(name = "Cube")
    public List<CubeRates> getCubeRates() {
        return cubeRates;
    }

    public String getTime() {
        return time;
    }
}
