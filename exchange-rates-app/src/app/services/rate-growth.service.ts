import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {CurrencyGrowth} from '../CurrencyGrowth';

@Injectable({
  providedIn: 'root'
})
export class RateGrowthService {

  private url_growth = 'http://localhost:8080/api/currencyRates/top5/growth'
  private url_movement = 'http://localhost:8080/api/currencyRates/top5/movement'
  constructor() {}


  fetchTop5MovingRates(): Observable<CurrencyGrowth[]> {
    return new Observable(observer => {
      fetch(this.url_movement)
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

  fetchTop5GrowingRates(): Observable<CurrencyGrowth[]> {
    return new Observable(observer => {
      fetch(this.url_growth)
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
