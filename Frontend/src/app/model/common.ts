import {FormControl} from "@angular/forms";
import {GeneralProfile} from "./auth";

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

export interface ReserveForm {
  seatIds: FormControl<string[]>;
  foodIds: FormControl<string[]>;
  reservationStart: FormControl<string>;
  reservationEnd: FormControl<string>;
}

export interface ReservationRequest {
  seatIds: string[];
  foodIds: string[];
  reservationStart: string;
  reservationEnd: string;
}

export interface Reservation {
  id: string;
  seats: Seating[];
  generalProfile: GeneralProfile;
  foods: Food[];
  reservationStart: string;
  reservationEnd: string;
}
