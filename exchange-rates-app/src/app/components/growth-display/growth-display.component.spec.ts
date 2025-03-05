import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GrowthDisplayComponent } from './growth-display.component';

describe('GrowthDisplayComponent', () => {
  let component: GrowthDisplayComponent;
  let fixture: ComponentFixture<GrowthDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GrowthDisplayComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GrowthDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
