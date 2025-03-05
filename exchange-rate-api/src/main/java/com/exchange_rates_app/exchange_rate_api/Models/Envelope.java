package com.exchange_rates_app.exchange_rate_api.Models;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "Envelope", namespace = "http://www.gesmes.org/xml/2002-08-01")
public class Envelope {


    private CubeContainer cubeContainer;

    @XmlElement(name = "Cube", namespace = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref")
    public CubeContainer getCubeContainer() {
        return cubeContainer;
    }
    public void setCubeContainer(CubeContainer cubeContainer) {
        this.cubeContainer = cubeContainer;
    }
}
