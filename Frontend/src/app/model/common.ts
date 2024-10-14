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
}
