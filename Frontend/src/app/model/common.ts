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
