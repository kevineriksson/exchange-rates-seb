package com.exchange_rates_app.exchange_rate_api.Models;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "Envelope")
public class Envelope {

    private CubeContainer cubeContainer;

    @XmlElement(name = "Cube")
    public CubeContainer getCubeContainer() {
        return cubeContainer;
    }
    public void setCubeContainer(CubeContainer cubeContainer) {
        this.cubeContainer = cubeContainer;
    }
}
