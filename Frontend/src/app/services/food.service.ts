import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Allergen, CreateFoodRequest, Food} from "../model/common";
import {catchError, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  private readonly baseUrl = '/api/food';

  constructor(private readonly http: HttpClient) {
  }

  public getFoods(pageNumber?: number) {
    return this.http.get<Food[]>(this.baseUrl)
      .pipe(catchError(err => {
        console.error('getFoods#', err);
        return of([] as Food[]);
      }));
  }

  public getAllergens() {
    return this.http.get<Allergen[]>(`${this.baseUrl}/allergens`);
  }

  public createFood(food: CreateFoodRequest) {
    return this.http.post<void>(this.baseUrl, food);
  }

  public editFood(id: string, food: CreateFoodRequest) {
    return this.http.put<void>(`${this.baseUrl}/${id}`, food)
  }

  public removeFood(id: string) {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
