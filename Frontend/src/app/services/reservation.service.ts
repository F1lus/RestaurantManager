import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Reservation, ReservationRequest} from "../model/common";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private readonly baseUrl = "/api/reservation";

  constructor(private readonly http: HttpClient) {
  }

  public getReservations() {
    return this.http.get<Reservation[]>(this.baseUrl);
  }

  public createReservation(reservation: ReservationRequest) {
    return this.http.post<void>(this.baseUrl, reservation);
  }

  public modifyReservation(id: string, reservation: ReservationRequest) {
    return this.http.put<void>(`${this.baseUrl}/${id}`, reservation);
  }

  public deleteReservation(id: string) {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
