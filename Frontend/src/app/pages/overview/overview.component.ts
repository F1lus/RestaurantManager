import {Component, OnInit} from '@angular/core';
import {SelectableListComponent} from "../../components/selectable-list/selectable-list.component";
import {Reservation} from "../../model/common";
import {ActivatedRoute} from "@angular/router";
import {ReservationService} from "../../services/reservation.service";
import {map, take} from "rxjs";
import {DatePipe, NgClass, NgOptimizedImage} from "@angular/common";
import {ArrayPipe} from "../../pipes/array.pipe";
import {TranslateModule} from "@ngx-translate/core";
import {ReserveFormComponent} from "../../components/reserve-form/reserve-form.component";
import {DateTime} from "luxon";
import {GeneralProfile} from "../../model/auth";
import {EditProfileFormComponent} from "../../components/edit-profile-form/edit-profile-form.component";

@Component({
  selector: 'app-overview',
  standalone: true,
  imports: [
    SelectableListComponent,
    ArrayPipe,
    TranslateModule,
    DatePipe,
    NgOptimizedImage,
    ReserveFormComponent,
    EditProfileFormComponent,
    NgClass
  ],
  templateUrl: './overview.component.html',
})
export class OverviewComponent implements OnInit {

  public readonly displayColumns = ['generalProfile', 'seating', 'foods', 'reservation', 'operations'] as const;
  public reservations: Reservation[] = [];
  public profile!: GeneralProfile;
  public selectedReservation?: Reservation;

  public readonly NOW = DateTime.now();

  constructor(
    private readonly route: ActivatedRoute,
    private readonly reservationService: ReservationService,
  ) {
  }

  public ngOnInit() {
    this.route.data
      .pipe(
        take(1),
        map(data => ({
          reservations: data['reservations'] as Reservation[],
          profile: data['profile'] as GeneralProfile,
        })),
      )
      .subscribe(({reservations, profile}) => {
        this.reservations = reservations;
        this.profile = profile;
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

  public areOperationsEnabled(startDate: string) {
    const start = DateTime.fromISO(startDate);
    const now = DateTime.now();
    const diff = start.diffNow(['minutes', 'hours']);
    return !(diff.hours === 0 && diff.minutes <= 30) && start.toMillis() > now.toMillis();
  }

  public isReservationInactive(endDate: string) {
    const now = DateTime.now();
    const end = DateTime.fromISO(endDate);

    return end.toMillis() <= now.toMillis();
  }
}
