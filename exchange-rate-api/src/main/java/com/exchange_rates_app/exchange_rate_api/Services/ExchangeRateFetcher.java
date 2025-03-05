package com.exchange_rates_app.exchange_rate_api.Services;

import com.exchange_rates_app.exchange_rate_api.Models.CubeContainer;
import com.exchange_rates_app.exchange_rate_api.Models.Envelope;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;

@Component
public class ExchangeRateFetcher {

    public static Envelope parseXML(String url) throws JAXBException, IOException {
        URL urlObj = new URL(url);
        InputStream inputStream = urlObj.openStream();
        JAXBContext context = JAXBContext.newInstance(Envelope.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Envelope) unmarshaller.unmarshal(inputStream);
    }
}
