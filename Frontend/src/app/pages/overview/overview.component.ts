import {Component, OnInit} from '@angular/core';
import {SelectableListComponent} from "../../components/selectable-list/selectable-list.component";
import {Reservation} from "../../model/common";
import {ActivatedRoute} from "@angular/router";
import {ReservationService} from "../../services/reservation.service";
import {map, take} from "rxjs";
import {DatePipe, NgOptimizedImage} from "@angular/common";
import {ArrayPipe} from "../../pipes/array.pipe";
import {TranslateModule} from "@ngx-translate/core";
import {ReserveFormComponent} from "../../components/reserve-form/reserve-form.component";

@Component({
  selector: 'app-overview',
  standalone: true,
  imports: [
    SelectableListComponent,
    ArrayPipe,
    TranslateModule,
    DatePipe,
    NgOptimizedImage,
    ReserveFormComponent
  ],
  templateUrl: './overview.component.html',
})
export class OverviewComponent implements OnInit {

  public readonly displayColumns = ['generalProfile', 'seating', 'foods', 'reservation', 'operations'] as const;
  public reservations: Reservation[] = [];
  public selectedReservation?: Reservation;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly reservationService: ReservationService,
  ) {
  }

  public ngOnInit() {
    this.route.data
      .pipe(
        take(1),
        map(data => data['reservations'] as Reservation[]),
      )
      .subscribe(reservations => {
        this.reservations = reservations;
      });
  }

  public edit(index: number) {
    if (index < 0 || index >= this.reservations.length) {
      return;
    }
    this.selectedReservation = this.reservations[index];
  }

  public remove(index: number) {
    if (index < 0 || index >= this.reservations.length) {
      return;
    }
    const reservationId = this.reservations[index].id;

    this.reservationService.deleteReservation(reservationId).subscribe(() => {
      this.reservations.splice(index, 1);
    })
  }

  public handleClose() {
    this.selectedReservation = undefined;
  }

  public handleEdit() {
    this.reservationService.getReservations()
      .subscribe(reservations => {
        this.reservations = reservations;
      })
  }
}
