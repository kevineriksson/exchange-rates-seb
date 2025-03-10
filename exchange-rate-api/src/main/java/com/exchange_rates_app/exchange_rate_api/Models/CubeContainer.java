package com.exchange_rates_app.exchange_rate_api.Models;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class CubeContainer {

    @XmlElement(name = "Cube", namespace = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref")
    private List<CubeWrapper> cubeWrappers;

    public List<CubeWrapper> getCubeWrappers() {
        return cubeWrappers;
    }
    public void setCubeWrappers(List<CubeWrapper> cubeWrappers) {
        this.cubeWrappers = cubeWrappers;
    }
}
