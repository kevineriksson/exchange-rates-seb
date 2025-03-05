import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ExchangeRateService {

  private url = 'http://localhost:8080/api/currency-rates'
  constructor(private http: HttpClient) {}

  public getCurrencies() {
    return this.http.get<any>(this.url);
  }
}
