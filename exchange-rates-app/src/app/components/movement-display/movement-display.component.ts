import {Component, OnInit} from '@angular/core';
import {CurrencyGrowth} from '../../CurrencyGrowth';
import {RateGrowthService} from '../../services/rate-growth.service';
import {DecimalPipe, NgClass, NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-movement-display',
  imports: [
    DecimalPipe,
    NgForOf,
    NgIf,
    NgClass
  ],
  templateUrl: './movement-display.component.html',
  styleUrl: './movement-display.component.css'
})
export class MovementDisplayComponent implements OnInit{
  movementRates: CurrencyGrowth[] = [];
  constructor(private rateGrowthService: RateGrowthService) {}

  ngOnInit() {
    this.rateGrowthService.fetchTop5MovingRates().subscribe({
      next: (rates) => {
        this.movementRates = rates;
      },
      error: (err) => {
        console.error('Error fetching currency rates', err);
      }
    });
  }
}
