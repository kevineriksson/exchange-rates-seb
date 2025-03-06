import { Component } from '@angular/core';
import {CurrencyConverterComponent} from './components/currency-converter/currency-converter.component';
import {GrowthDisplayComponent} from './components/growth-display/growth-display.component';
import {MovementDisplayComponent} from './components/movement-display/movement-display.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CurrencyConverterComponent, GrowthDisplayComponent, MovementDisplayComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'exchange-rates-app';
}
