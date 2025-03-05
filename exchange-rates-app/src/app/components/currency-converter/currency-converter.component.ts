import {Component, effect, OnInit, signal} from '@angular/core';
import {ExchangeRateService} from '../../services/exchange-rate.service';
import {CurrencyRate} from '../../CurrencyRate';
import {CommonModule, DecimalPipe} from '@angular/common';

@Component({
  selector: 'app-currency-converter',
  standalone: true,
  imports: [
    CommonModule,
    DecimalPipe
  ],
  templateUrl: './currency-converter.component.html',
  styleUrl: './currency-converter.component.css'
})
export class CurrencyConverterComponent implements OnInit {
  currencyRates: CurrencyRate[] = [];
  amount: number = 1;
  selectedCurrency: string = '';
  loading: boolean = true;

  constructor(private currencyService: ExchangeRateService) {}

  ngOnInit() {
    this.currencyService.fetchCurrencyRates().subscribe({
      next: (rates) => {
        this.currencyRates = rates;
        this.loading = false;
        if (rates.length > 0) {
          this.selectedCurrency = rates[0].currency;
        }
      },
      error: (err) => {
        console.error('Error fetching currency rates', err);
        this.loading = false;
      }
    });
  }

  get convertedAmount(): number | null {
    if (!this.amount) return null;

    const selectedRate = this.currencyRates.find(
      rate => rate.currency === this.selectedCurrency
    );

    return selectedRate
      ? this.amount * selectedRate.rate
      : null;
  }
}
