import {ResolveFn} from "@angular/router";
import {Reservation} from "../model/common";
import {inject} from "@angular/core";
import {ReservationService} from "../services/reservation.service";

export const reservationResolver: ResolveFn<Reservation[]> = () => {
  return inject(ReservationService).getReservations();
};
