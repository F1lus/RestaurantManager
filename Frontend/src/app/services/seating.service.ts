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

  public modifySeat(seat: Seating) {
    const {name, personCount} = seat;
    return this.http.put(`${this.baseUrl}/${seat.id}`, {name, personCount})
  }

  public createSeat(seat: Partial<Seating>) {
    const {name, personCount} = seat;
    return this.http.post(this.baseUrl, {name, personCount});
  }

  public deleteSeat(id: string) {
    return this.http.delete(`${this.baseUrl}/${id}`)
  }
}
