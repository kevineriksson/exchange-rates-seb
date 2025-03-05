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
import java.util.Scanner;

@Component
public class ExchangeRateFetcher {

    public static Envelope parseXML(String url) throws JAXBException, IOException {
        URL urlObj = new URL(url);
        InputStream inputStream = urlObj.openStream();
        String xmlContentWithoutNamespaces = removeNamespaces(inputStream);

        JAXBContext context = JAXBContext.newInstance(Envelope.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Envelope envelope = (Envelope) unmarshaller.unmarshal(new StringReader(xmlContentWithoutNamespaces));

        if (envelope == null) {
            System.out.println("Failed to parse Envelope.");
            return null;
        }
        return envelope;
    }

    private static String removeNamespaces(InputStream inputStream) throws IOException {
        StringBuilder xmlContent = new StringBuilder();
        int byteRead;
        while ((byteRead = inputStream.read()) != -1) {
            xmlContent.append((char) byteRead);
        }

        String newXml = xmlContent.toString()
                .replaceAll(" xmlns:gesmes=\".*?\"", "") // Remove gesmes namespace
                .replaceAll(" xmlns=\".*?\"", "") // Remove default namespace
                .replaceAll("<gesmes:[^>]+>", "") // Remove any gesmes prefixed tags
                .replaceAll("</gesmes:[^>]+>", "") // Remove any gesmes prefixed closing tags
                .replaceAll("<subject>.*?</subject>", "") // Remove <subject>
                .replaceAll("<Sender>.*?</Sender>", "") // Remove <Sender>
                .replaceAll("<name>.*?</name>", ""); // Remove <name>
        System.out.println(newXml);
        return newXml;
    }
}
