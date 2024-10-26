import {Component, OnDestroy, OnInit} from '@angular/core';
import {NgClass} from "@angular/common";
import {SelectableListComponent} from "../../components/selectable-list/selectable-list.component";
import {DashboardState} from "../../model/common";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {DashboardNavigationComponent} from "../../components/dashboard-navigation/dashboard-navigation.component";
import {ManageUsersComponent} from "../../components/manage-users/manage-users.component";
import {ManageSeatsComponent} from "../../components/manage-seats/manage-seats.component";
import {ManageMenuComponent} from "../../components/manage-menu/manage-menu.component";
import {FoodFormComponent} from "../../components/food-form/food-form.component";
import {SeatFormComponent} from "../../components/seat-form/seat-form.component";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    NgClass,
    SelectableListComponent,
    DashboardNavigationComponent,
    ManageUsersComponent,
    ManageSeatsComponent,
    ManageMenuComponent,
    FoodFormComponent,
    SeatFormComponent
  ],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnInit, OnDestroy {

  public state?: DashboardState;
  protected readonly DashboardState = DashboardState;
  private subscription: Subscription = new Subscription();

  constructor(
    private readonly route: ActivatedRoute,
    private readonly router: Router,
  ) {
  }

  public ngOnInit(): void {
    this.subscription.add(
      this.route.queryParamMap.subscribe(data => {
        if (!data.get('state')) {
          void this.router.navigate([], {
            queryParams: {state: DashboardState.MODIFY_USER},
            queryParamsHandling: 'merge'
          });
          return;
        }

        const state = data.get('state') as (DashboardState | null);
        this.state = state ?? undefined;
      })
    );
  }

  public ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
