package com.exchange_rates_app.exchange_rate_api.Models;

import java.util.List;

public class Envelope {

    private List<Cube> cube;

    public List<Cube> getCube() {
        return cube;
    }
    public void setCube(List<Cube> cube) {
        this.cube = cube;
    }
}
