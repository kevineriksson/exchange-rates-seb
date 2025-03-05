import {Component, OnInit} from '@angular/core';
import {ExchangeRateService} from '../../services/exchange-rate.service';

@Component({
  selector: 'app-currency-converter',
  imports: [],
  templateUrl: './currency-converter.component.html',
  styleUrl: './currency-converter.component.css'
})
export class CurrencyConverterComponent implements OnInit{

  fromCurrency = 'EUR';
  toCurrency = 'USD';
  amount = 1;
  convertedAmount = 2;
  constructor(private exchangeRateService: ExchangeRateService) {}

  ngOnInit() {
    this.exchangeRateService.getCurrencies().subscribe()
  }
}
