package com.exchange_rates_app.exchange_rate_api;

import com.exchange_rates_app.exchange_rate_api.Models.CurrencyGrowth;
import com.exchange_rates_app.exchange_rate_api.Repos.ExchangeRateRepo;
import com.exchange_rates_app.exchange_rate_api.Services.ExchangeRateScheduler;
import com.exchange_rates_app.exchange_rate_api.Services.ExchangeRateService;
import jakarta.xml.bind.JAXBException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ExchangeRateApiApplication implements CommandLineRunner {

	private final ExchangeRateScheduler exchangeRateScheduler;

    public ExchangeRateApiApplication(ExchangeRateScheduler exchangeRateScheduler) {
        this.exchangeRateScheduler = exchangeRateScheduler;
    }

    public static void main(String[] args) {
		SpringApplication.run(ExchangeRateApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		exchangeRateScheduler.fetchAndSaveRatesDaily();
	}
}
