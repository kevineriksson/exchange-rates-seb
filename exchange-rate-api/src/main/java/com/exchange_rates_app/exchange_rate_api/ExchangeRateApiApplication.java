package com.exchange_rates_app.exchange_rate_api;

import jakarta.xml.bind.JAXBException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ExchangeRateApiApplication {

	public static void main(String[] args) throws {

		SpringApplication.run(ExchangeRateApiApplication.class, args);
	}

}
