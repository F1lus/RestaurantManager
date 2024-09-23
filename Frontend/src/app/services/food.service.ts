import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Food} from "../model/common";
import {catchError, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  private readonly BASE_URL = '/api/food';

  constructor(private readonly http: HttpClient) {
  }

  public getFoods(pageNumber?: number) {
    return this.http.get<Food[]>(this.BASE_URL)
      .pipe(catchError(err => {
        console.error('getFoods#', err);
        return of([] as Food[]);
      }));
  }
}