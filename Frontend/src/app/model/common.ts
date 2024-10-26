import {FormControl} from "@angular/forms";

export interface Allergen {
  id: string;
  name: string;
}

export interface Food {
  id: string;
  name: string;
  description: string;
  price: number;
  allergens: Allergen[];
}

export enum DashboardState {
  MODIFY_USER = 'MODIFY_USER',
  MODIFY_SEAT = 'MODIFY_SEAT',
  MODIFY_MENU = 'MODIFY_MENU',
  ADD_MENU = 'ADD_MENU',
  ADD_SEAT = 'ADD_SEAT',
}

export interface FoodForm {
  name: FormControl<string>;
  description: FormControl<string>;
  price: FormControl<number>;
  allergens: FormControl<string[]>;
}

export interface CreateFoodRequest {
  name: string;
  description: string;
  price: number;
  allergens: string[];
}

export interface Seating {
  id: string;
  name: string;
  personCount: number;
}

export interface SeatForm {
  name: FormControl<string>;
  personCount: FormControl<number>;
}
