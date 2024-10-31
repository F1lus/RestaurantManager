import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Food, Reservation, ReservationRequest, ReserveForm, Seating} from "../../model/common";
import {DateTime} from "luxon";
import {map, Subscription, take} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {ReservationService} from "../../services/reservation.service";
import {reservationTimeValidation} from "../../util/ReservationTimeValidation";
import {PricePipe} from "../../pipes/price.pipe";
import {TranslateModule} from "@ngx-translate/core";

@Component({
  selector: 'app-reserve-form',
  standalone: true,
  imports: [
    PricePipe,
    ReactiveFormsModule,
    TranslateModule
  ],
  templateUrl: './reserve-form.component.html',
  styleUrl: './reserve-form.component.scss'
})
export class ReserveFormComponent implements OnInit, OnChanges, OnDestroy {

  @Input() public reservation?: Reservation;
  @Input() public isEditing = false;

  @Output() public readonly submit = new EventEmitter<void>();
  @Output() public readonly close = new EventEmitter<void>();

  public reserveForm!: FormGroup<ReserveForm>;

  public totalCost = 0;
  public menu: Food[] = [];
  public seats: Seating[] = [];

  public readonly minDate = DateTime.now().set({minute: 0, hour: 0}).plus({day: 1}).toISO().substring(0, 16);
  public readonly maxDate = DateTime.now().plus({week: 2}).toISO().substring(0, 16);

  private readonly subscription = new Subscription();

  constructor(
    private readonly route: ActivatedRoute,
    private readonly router: Router,
    private readonly formBuilder: FormBuilder,
    private readonly reservationService: ReservationService,
  ) {
  }

  private get request() {
    if (this.isEditing && this.reservation) {
      return this.reservationService.modifyReservation(this.reservation!.id, this.reserveForm.value as ReservationRequest);
    }
    return this.reservationService.createReservation(this.reserveForm.value as ReservationRequest)
  }

  public ngOnInit() {
    this.reserveForm = this.formBuilder.group({
      seatIds: this.formBuilder.nonNullable.control<string[]>([], [Validators.required]),
      foodIds: this.formBuilder.nonNullable.control<string[]>([], []),
      reservationStart: this.formBuilder.nonNullable.control('', [Validators.required]),
      reservationEnd: this.formBuilder.nonNullable.control('', [Validators.required]),
    }, {validators: [reservationTimeValidation()]});

    this.updateForm();

    this.subscription.add(
      this.reserveForm.controls
        .foodIds
        .valueChanges
        .subscribe(values => {
          this.totalCost = this.menu
            .filter(food => values.includes(food.id))
            .map(food => food.price)
            .reduce((a, b) => a + b, 0);
        })
    );

    this.route.data
      .pipe(
        take(1),
        map(data => ({
          menu: data['menu']['foods'] as Food[],
          seats: data['seats'] as Seating[],
        }))
      )
      .subscribe(({menu, seats}) => {
        this.menu = menu;
        this.seats = seats;
      })
  }

  public ngOnChanges(changes: SimpleChanges) {
    if (!changes['reservation'].isFirstChange()) {
      this.updateForm();
    }
  }

  public ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  public handleSubmit() {
    this.request.subscribe(() => {
      if (this.isEditing) {
        this.submit.emit();
        this.close.emit();
        return;
      }

      void this.router.navigate(['/overview']);
    })
  }

  public handleCancel() {
    if (this.isEditing) {
      this.close.emit();
    }

    void this.router.navigate(['/overview']);
  }

  public timeFormatter(input: 'reservationStart' | 'reservationEnd') {
    const value = this.reserveForm.get(input)!.value;
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
      this.reserveForm.patchValue({
        [input]: luxonTime.toISO()!.substring(0, 16),
      });
    }
  }

  private updateForm() {
    this.reserveForm.patchValue({
      seatIds: this.reservation?.seats.map(seat => seat.id) ?? [],
      foodIds: this.reservation?.foods.map(food => food.id) ?? [],
      reservationStart: this.reservation?.reservationStart ?? '',
      reservationEnd: this.reservation?.reservationEnd ?? '',
    });
  }

}
