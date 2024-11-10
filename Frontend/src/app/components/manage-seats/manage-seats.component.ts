import {Component, DestroyRef, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {BehaviorSubject, map, switchMap, take} from "rxjs";
import {DashboardState, Seating} from "../../model/common";
import {SelectableListComponent} from "../selectable-list/selectable-list.component";
import {NgOptimizedImage} from "@angular/common";
import {SeatingService} from "../../services/seating.service";
import {SeatFormComponent} from "../seat-form/seat-form.component";
import {TranslateModule} from "@ngx-translate/core";
import {SeatFilterComponent} from "../seat-filter/seat-filter.component";
import {takeUntilDestroyed} from "@angular/core/rxjs-interop";

@Component({
  selector: 'app-manage-seats',
  standalone: true,
  imports: [
    SelectableListComponent,
    RouterLink,
    NgOptimizedImage,
    SeatFormComponent,
    TranslateModule,
    SeatFilterComponent
  ],
  templateUrl: './manage-seats.component.html',
})
export class ManageSeatsComponent implements OnInit {

  public displaySeats: Seating[] = [];
  public selectedSeat?: Seating;

  public readonly displayedColumns = ['name', 'personCount'] as const;

  protected readonly DashboardState = DashboardState;

  private seats: Seating[] = [];
  private readonly filterSubject = new BehaviorSubject<Partial<Seating>>({});

  constructor(
    private readonly route: ActivatedRoute,
    private readonly seatingService: SeatingService,
    private readonly destroyRef: DestroyRef
  ) {
  }

  public ngOnInit() {
    this.route.data
      .pipe(take(1), map(data => data['seats'] as Seating[]))
      .subscribe(seats => {
        this.seats = seats;
        this.displaySeats = seats;
      });

    this.filterSubject.pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((filter) => {
        if (!filter.name && !filter.personCount) {
          return;
        }

        this.displaySeats = this.seats
          .filter(seat => seat.name.toLowerCase().includes(filter.name?.toLowerCase() ?? ''))
          .filter(seat => seat.personCount >= (filter.personCount ?? 1));
      })
  }

  public edit(index: number) {
    if (index < 0 || index >= this.displaySeats.length) {
      return;
    }
    this.selectedSeat = this.displaySeats[index];
  }

  public remove(index: number) {
    if (index < 0 || index >= this.seats.length) {
      return;
    }
    const seatId = this.displaySeats[index].id;

    this.seatingService.deleteSeat(seatId)
      .pipe(switchMap(() => this.seatingService.getSeats()))
      .subscribe(seats => {
        this.seats = seats;
        this.displaySeats = seats;
        this.filterSubject.next(this.filterSubject.value);
        if (this.selectedSeat?.id === seatId) {
          this.selectedSeat = undefined;
        }
      })
  }

  public handleSeatEdit() {
    this.seatingService.getSeats()
      .subscribe(seats => {
        this.seats = seats;
        this.displaySeats = seats;
        this.filterSubject.next(this.filterSubject.value);
      });
  }

  public handleClose() {
    this.selectedSeat = undefined;
  }

  public handleFilter(filter: Partial<Seating>) {
    this.filterSubject.next(filter);
  }
}
