import {Component, OnInit} from '@angular/core';
import {RateGrowthService} from '../../services/rate-growth.service';
import {CurrencyGrowth} from '../../CurrencyGrowth';
import {CommonModule, DecimalPipe} from '@angular/common';

@Component({
  selector: 'app-growth-display',
  imports: [
    DecimalPipe,
    CommonModule
  ],
  templateUrl: './growth-display.component.html',
  styleUrl: './growth-display.component.css',
  standalone: true
})
export class GrowthDisplayComponent implements OnInit{
  growthRates: CurrencyGrowth[] = [];
  constructor(private rateGrowthService: RateGrowthService) {}

  ngOnInit() {
    this.rateGrowthService.fetchTop5GrowingRates().subscribe({
      next: (rates) => {
        this.growthRates = rates;
      },
      error: (err) => {
        console.error('Error fetching currency rates', err);
      }
    });
  }
}
