import {Injectable, signal} from '@angular/core';
import {CurrencyRate} from '../CurrencyRate';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExchangeRateService {

  private url = 'http://localhost:8080/api/currencyRates'
  constructor() {}


  fetchCurrencyRates(): Observable<CurrencyRate[]> {
    return new Observable(observer => {
      fetch(this.url)
        .then(response => {
          if (!response.ok) {
            throw new Error('Failed to fetch currency rates');
          }
          return response.json();
        })
        .then(data => {
          observer.next(data);
          observer.complete();
        })
        .catch(error => {
          observer.error(error);
        });
    });
  }
}
