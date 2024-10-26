import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {map, take} from "rxjs";
import {DashboardState, Seating} from "../../model/common";
import {SelectableListComponent} from "../selectable-list/selectable-list.component";
import {NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-manage-seats',
  standalone: true,
  imports: [
    SelectableListComponent,
    RouterLink,
    NgOptimizedImage
  ],
  templateUrl: './manage-seats.component.html',
})
export class ManageSeatsComponent implements OnInit {

  public seats: Seating[] = [];
  public readonly displayedColumns = ['name', 'personCount'] as const;
  protected readonly DashboardState = DashboardState;

  constructor(
    private readonly route: ActivatedRoute,
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

  }

  public remove(index: number) {

  }
}
