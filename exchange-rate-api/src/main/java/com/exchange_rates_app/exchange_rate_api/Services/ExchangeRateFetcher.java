package com.exchange_rates_app.exchange_rate_api.Services;

import com.exchange_rates_app.exchange_rate_api.Models.Envelope;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.IOException;
import java.net.URL;

public class ExchangeRateFetcher {

    public static Envelope parseXML(String url) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Envelope.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        URL urlObj = new URL(url);
        return (Envelope) unmarshaller.unmarshal(urlObj.openStream());
    }
}
