package com.exchange_rates_app.exchange_rate_api;

import com.exchange_rates_app.exchange_rate_api.Services.ExchangeRateScheduler;
import com.exchange_rates_app.exchange_rate_api.Services.ExchangeRateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		exchangeRateScheduler.fetchAndSaveRates90Days();
	}
}
