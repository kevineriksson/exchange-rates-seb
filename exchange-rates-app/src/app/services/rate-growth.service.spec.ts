import { TestBed } from '@angular/core/testing';

import { RateGrowthService } from './rate-growth.service';

describe('RateGrowthService', () => {
  let service: RateGrowthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RateGrowthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
