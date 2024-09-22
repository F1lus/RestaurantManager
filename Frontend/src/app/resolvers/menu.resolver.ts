import {ResolveFn} from '@angular/router';
import {inject} from "@angular/core";
import {FoodService} from "../services/food.service";
import {Food} from "../model/common";
import {of, switchMap} from "rxjs";

interface FoodResolver {
  foods: Food[];
  currentPage: number;
}

export const menuResolver: ResolveFn<FoodResolver> = (route, state) => {
  const page = route.queryParamMap.get('page');
  const currentPage = page ? +page : 1;
  return inject(FoodService).getFoods(currentPage)
    .pipe(
      switchMap(foods =>
        of({
          foods,
          currentPage
        }))
    );
};
