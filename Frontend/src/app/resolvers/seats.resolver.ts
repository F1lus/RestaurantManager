import {ResolveFn} from "@angular/router";
import {Seating} from "../model/common";
import {inject} from "@angular/core";
import {SeatingService} from "../services/seating.service";

export const seatsResolver: ResolveFn<Seating[]> = () => {
  return inject(SeatingService).getSeats();
};
