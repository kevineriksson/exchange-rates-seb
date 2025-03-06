package com.exchange_rates_app.exchange_rate_api.Repos;

import com.exchange_rates_app.exchange_rate_api.Models.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRateRepo extends JpaRepository<CurrencyRate, Long> {
    @Query("SELECT MAX(c.date) FROM CurrencyRate c")
    LocalDate findLatestDate();

    @Query("SELECT c FROM CurrencyRate c WHERE c.date = :latestDate")
    List<CurrencyRate> findByDate(@Param("latestDate") LocalDate latestDate);

}
