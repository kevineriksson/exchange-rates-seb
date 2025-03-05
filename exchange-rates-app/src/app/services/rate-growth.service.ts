import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {CurrencyGrowth} from '../CurrencyGrowth';

@Injectable({
  providedIn: 'root'
})
export class RateGrowthService {

  private url = 'http://localhost:8080/api/currencyRates/top5'
  constructor() {}


  fetchTop5MovingRates(): Observable<CurrencyGrowth[]> {
    return new Observable(observer => {
      fetch(this.url)
        .then(response => {
          if (!response.ok) {
            throw new Error('Failed to fetch growth rates');
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
