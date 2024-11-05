import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {map, take} from "rxjs";
import {DashboardState, Seating} from "../../model/common";
import {SelectableListComponent} from "../selectable-list/selectable-list.component";
import {NgOptimizedImage} from "@angular/common";
import {SeatingService} from "../../services/seating.service";
import {SeatFormComponent} from "../seat-form/seat-form.component";
import {TranslateModule} from "@ngx-translate/core";

@Component({
  selector: 'app-manage-seats',
  standalone: true,
  imports: [
    SelectableListComponent,
    RouterLink,
    NgOptimizedImage,
    SeatFormComponent,
    TranslateModule
  ],
  templateUrl: './manage-seats.component.html',
})
export class ManageSeatsComponent implements OnInit {

  public seats: Seating[] = [];
  public selectedSeat?: Seating;
  public readonly displayedColumns = ['name', 'personCount'] as const;
  protected readonly DashboardState = DashboardState;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly seatingService: SeatingService,
  ) {
  }

  public ngOnInit() {
    this.route.data
      .pipe(take(1), map(data => data['seats'] as Seating[]))
      .subscribe(seats => {
        this.seats = seats;
      })
  }

  public edit(index: number) {
    if (index < 0 || index >= this.seats.length) {
      return;
    }
    this.selectedSeat = this.seats[index];
  }

  public remove(index: number) {
    if (index < 0 || index >= this.seats.length) {
      return;
    }
    const seatId = this.seats[index].id;

    this.seatingService.deleteSeat(seatId)
      .subscribe(() => {
        this.seats.splice(index, 1);

        if (this.selectedSeat?.id === seatId) {
          this.selectedSeat = undefined;
        }
      })
  }

  public handleSeatEdit() {
    this.seatingService.getSeats()
      .subscribe(seats => {
        this.seats = seats;
      });
  }

  public handleClose() {
    this.selectedSeat = undefined;
  }
}
