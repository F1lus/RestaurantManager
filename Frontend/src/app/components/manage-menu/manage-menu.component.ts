import {Component, OnInit} from '@angular/core';
import {DashboardState, Food} from "../../model/common";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {map, take} from "rxjs";
import {SelectableListComponent} from "../selectable-list/selectable-list.component";
import {NgOptimizedImage} from "@angular/common";
import {FoodService} from "../../services/food.service";
import {FoodFormComponent} from "../food-form/food-form.component";
import {TranslateModule} from "@ngx-translate/core";

@Component({
  selector: 'app-manage-menu',
  standalone: true,
  imports: [
    SelectableListComponent,
    NgOptimizedImage,
    FoodFormComponent,
    RouterLink,
    TranslateModule,
  ],
  templateUrl: './manage-menu.component.html',
})
export class ManageMenuComponent implements OnInit {

  public readonly displayedColumns = ['name', 'price', 'allergens'] as const;
  public menu: Food[] = [];
  public selectedFood?: Food;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly foodService: FoodService,
  ) {
  }

  public ngOnInit(): void {
    this.route.data
      .pipe(take(1), map(data => data['menu']['foods'] as Food[]))
      .subscribe(data => this.menu = data);
  }
  protected readonly DashboardState = DashboardState;

  public remove(index: number) {
    if (index < 0 || index >= this.menu.length) {
      return;
    }
    const foodId = this.menu[index].id;

    this.foodService.removeFood(foodId)
      .subscribe(() => {
        this.menu.splice(index, 1);
        if (this.selectedFood?.id === foodId) {
          this.selectedFood = undefined;
        }
      })
  }

  public edit(index: number) {
    this.handleClose();
    if (index < 0 || index >= this.menu.length) {
      return;
    }
    this.selectedFood = this.menu[index];
  }

  public handleFoodEdit() {
    this.foodService.getFoods()
      .subscribe(foods => {
        this.menu = foods;
      });
  }

  public handleClose() {
    this.selectedFood = undefined;
  }
}
