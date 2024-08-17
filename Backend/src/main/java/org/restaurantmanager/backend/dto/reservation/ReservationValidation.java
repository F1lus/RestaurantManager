package org.restaurantmanager.backend.dto.reservation;

import org.restaurantmanager.backend.dto.ApplicationValidation;

public interface ReservationValidation extends ApplicationValidation {
    String RESERVATION_ID_REQUIRED = "reservation_id_required";
    String SEATING_ID_REQUIRED = "seating_id_required";
    String RESERVATION_START_REQUIRED = "reservation_start_required";
    String RESERVATION_END_REQUIRED = "reservation_end_required";
}
