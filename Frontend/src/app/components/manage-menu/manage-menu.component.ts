import {Component, DestroyRef, OnInit} from '@angular/core';
import {DashboardState, Food, FoodFilter} from "../../model/common";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {BehaviorSubject, map, switchMap, take} from "rxjs";
import {SelectableListComponent} from "../selectable-list/selectable-list.component";
import {NgOptimizedImage} from "@angular/common";
import {FoodService} from "../../services/food.service";
import {FoodFormComponent} from "../food-form/food-form.component";
import {TranslateModule} from "@ngx-translate/core";
import {FoodFilterComponent} from "../food-filter/food-filter.component";
import {takeUntilDestroyed} from "@angular/core/rxjs-interop";

@Component({
  selector: 'app-manage-menu',
  standalone: true,
  imports: [
    SelectableListComponent,
    NgOptimizedImage,
    FoodFormComponent,
    RouterLink,
    TranslateModule,
    FoodFilterComponent,
  ],
  templateUrl: './manage-menu.component.html',
})
export class ManageMenuComponent implements OnInit {

  public readonly displayedColumns = ['name', 'price', 'allergens'] as const;
  public displayMenu: Food[] = [];
  public selectedFood?: Food;

  protected readonly DashboardState = DashboardState;

  private menu: Food[] = [];

  private readonly filterSubject = new BehaviorSubject<FoodFilter | null>(null);

  constructor(
    private readonly route: ActivatedRoute,
    private readonly foodService: FoodService,
    private destroyRef: DestroyRef
  ) {
  }

  public ngOnInit(): void {
    this.route.data
      .pipe(take(1), map(data => data['menu']['foods'] as Food[]))
      .subscribe(data => {
        this.menu = data;
        this.displayMenu = data;
      });

    this.filterSubject.pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((filter) => {
        if (!filter) {
          return;
        }

        this.displayMenu = this.menu
          .filter(food => food.name.toLowerCase().includes(filter.name?.toLowerCase() ?? ''))
          .filter(food => filter.price > 0 ? food.price <= filter.price : true)
          .filter(food => filter.allergens.every(
            allergen => food.allergens.map(
              allergen => allergen.name.toLowerCase()).includes(allergen)
          ))
      })
  }

  public remove(index: number) {
    if (index < 0 || index >= this.menu.length) {
      return;
    }
    const foodId = this.displayMenu[index].id;

    this.foodService.removeFood(foodId)
      .pipe(switchMap(() => this.foodService.getFoods()))
      .subscribe(foods => {
        this.menu = foods;
        this.displayMenu = foods;
        this.filterSubject.next(this.filterSubject.value);
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
    this.selectedFood = this.displayMenu[index];
  }

  public handleFoodEdit() {
    this.foodService.getFoods()
      .subscribe(foods => {
        this.menu = foods;
        this.displayMenu = foods;
        this.filterSubject.next(this.filterSubject.value);
      });
  }

  public handleClose() {
    this.selectedFood = undefined;
  }

  public handleFilter(filter: FoodFilter) {
    this.filterSubject.next(filter);
  }
}
