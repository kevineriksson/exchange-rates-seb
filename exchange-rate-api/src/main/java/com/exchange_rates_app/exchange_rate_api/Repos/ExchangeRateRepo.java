package com.exchange_rates_app.exchange_rate_api.Repos;

import com.exchange_rates_app.exchange_rate_api.Models.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepo extends JpaRepository<CurrencyRate, Long> {
}
