import {ResolveFn} from "@angular/router";
import {inject} from "@angular/core";
import {FoodService} from "../services/food.service";
import {Allergen} from "../model/common";

export const allergensResolver: ResolveFn<Allergen[]> = () => {
  return inject(FoodService).getAllergens();
};
