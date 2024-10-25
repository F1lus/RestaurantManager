import {Component, OnInit} from '@angular/core';
import {DashboardState, Food} from "../../model/common";
import {ActivatedRoute, Router} from "@angular/router";
import {map, take} from "rxjs";
import {SelectableListComponent} from "../selectable-list/selectable-list.component";
import {NgOptimizedImage} from "@angular/common";
import {FoodService} from "../../services/food.service";

@Component({
  selector: 'app-manage-menu',
  standalone: true,
  imports: [
    SelectableListComponent,
    NgOptimizedImage,
  ],
  templateUrl: './manage-menu.component.html',
})
export class ManageMenuComponent implements OnInit {

  public menu: Food[] = [];

  constructor(
    private readonly route: ActivatedRoute,
    private readonly router: Router,
    private readonly foodService: FoodService,
  ) {
  }

  public ngOnInit(): void {
    this.route.data
      .pipe(take(1), map(data => data['menu']['foods'] as Food[]))
      .subscribe(data => this.menu = data);
  }

  public addNew() {
    void this.router.navigate([], {queryParams: {state: DashboardState.ADD_MENU}, queryParamsHandling: 'merge'});
  }

  public edit(index: number) {
    if (index < 0 || index >= this.menu.length) {
      return;
    }
    const menu = this.menu[index];
  }

  public remove(index: number) {
    if (index < 0 || index >= this.menu.length) {
      return;
    }
    const foodId = this.menu[index].id;

    this.foodService.removeFood(foodId)
      .subscribe(() => {
        this.menu.splice(index, 1);
      })
  }

}
