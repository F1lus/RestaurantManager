import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Seating} from "../model/common";

@Injectable({
  providedIn: 'root'
})
export class SeatingService {

  private readonly baseUrl = "/api/seating"

  constructor(private readonly http: HttpClient) {
  }

  public getSeats() {
    return this.http.get<Seating[]>(this.baseUrl);
  }
}
