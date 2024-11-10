import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SeatFilterComponent} from './seat-filter.component';

describe('SeatFilterComponent', () => {
  let component: SeatFilterComponent;
  let fixture: ComponentFixture<SeatFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeatFilterComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(SeatFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
