import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {Food, ReservationFilter, ReserveFilterForm, Seating} from "../../model/common";
import {DateTime} from "luxon";
import {TranslateModule} from "@ngx-translate/core";
import {ErrorPipe} from "../../pipes/error.pipe";
import {ActivatedRoute} from "@angular/router";
import {map, take} from "rxjs";

@Component({
  selector: 'app-reserve-filter',
  standalone: true,
  imports: [
    TranslateModule,
    ReactiveFormsModule,
    ErrorPipe
  ],
  templateUrl: './reserve-filter.component.html',
})
export class ReserveFilterComponent implements OnInit {

  @Output() public readonly filter = new EventEmitter<ReservationFilter>();

  public filterForm!: FormGroup<ReserveFilterForm>;
  public seats: Seating[] = [];
  public menu: Food[] = [];

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly route: ActivatedRoute,
  ) {
  }

  public ngOnInit() {
    this.filterForm = this.formBuilder.group({
      reservedBy: this.formBuilder.nonNullable.control(''),
      seatIds: this.formBuilder.nonNullable.control<string[]>([]),
      foodIds: this.formBuilder.nonNullable.control<string[]>([]),
      reservationStart: this.formBuilder.nonNullable.control(''),
      reservationEnd: this.formBuilder.nonNullable.control(''),
    });

    this.route.data
      .pipe(
        take(1),
        map(data => ({
          menu: data['menu']['foods'] as Food[],
          seats: data['seats'] as Seating[],
        }))
      ).subscribe(({menu, seats}) => {
      this.menu = menu;
      this.seats = seats;
    })
  }

  public handleSubmit() {
    this.filter.emit({
      reservedBy: this.filterForm.value.reservedBy?.trim().toLowerCase() ?? '',
      seatIds: this.filterForm.value.seatIds ?? [],
      foodIds: this.filterForm.value.foodIds ?? [],
      reservationStart: this.convertStringToDateTime(this.filterForm.value.reservationStart),
      reservationEnd: this.convertStringToDateTime(this.filterForm.value.reservationEnd),
    });
  }

  public timeFormatter(input: 'reservationStart' | 'reservationEnd') {
    const value = this.filterForm.get(input)!.value;
    let luxonTime = DateTime.fromISO(value);
    if (luxonTime.minute >= 0 && luxonTime.minute <= 15) {
      luxonTime = luxonTime.set({minute: 0});
    } else if (luxonTime.minute > 15 && luxonTime.minute <= 30) {
      luxonTime = luxonTime.set({minute: 30});
    } else if (luxonTime.minute > 30 && luxonTime.minute <= 45) {
      luxonTime = luxonTime.set({minute: 30});
    } else {
      luxonTime = luxonTime.set({minute: 60});
    }
    if (luxonTime.toISO()) {
      this.filterForm.patchValue({
        [input]: luxonTime.toISO()!.substring(0, 16),
      });
    }
  }

  private convertStringToDateTime(str?: string) {
    return !!str ? DateTime.fromISO(str) : null;
  }
}
