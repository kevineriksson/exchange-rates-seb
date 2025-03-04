package com.exchange_rates_app.exchange_rate_api.Models;

import java.util.List;

public class Cube {
    private String time;
    private List<CubeRates> cubeRates;

    public List<CubeRates> getCubeRates() {
        return cubeRates;
    }

    public void setCubeRates(List<CubeRates> cubeRates) {
        this.cubeRates = cubeRates;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
