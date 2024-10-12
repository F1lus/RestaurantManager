package org.restaurantmanager.backend.datamodel.repository;

import org.restaurantmanager.backend.datamodel.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID>, JpaSpecificationExecutor<ReservationEntity> {

    @Query("select count(r) from ReservationEntity r " +
            "where r.reservationStart >= :reservationStart " +
            "and r.reservationEnd <= :reservationEnd " +
            "and r.seatingEntity.id = :seatId")
    Integer countReservationsInInterval(
            @Param("reservationStart") LocalDateTime reservationStart,
            @Param("reservationEnd") LocalDateTime reservationEnd,
            @Param("seatId") UUID seatId
    );

}
