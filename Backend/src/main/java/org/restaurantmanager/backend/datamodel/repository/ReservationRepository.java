package org.restaurantmanager.backend.datamodel.repository;

import org.restaurantmanager.backend.datamodel.entity.ReservationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID> {

    @Query("select count(r) from ReservationEntity r inner join r.seatingEntities s " +
            "where r.reservationStart < :reservationEnd " +
            "and r.reservationEnd > :reservationStart " +
            "and s.id in :seatIds")
    Integer countReservationsInInterval(
            @Param("reservationStart") LocalDateTime reservationStart,
            @Param("reservationEnd") LocalDateTime reservationEnd,
            @Param("seatIds") List<UUID> seatIds
    );

    @Query("select count(r) from ReservationEntity r inner join r.seatingEntities s " +
            "where r.reservationStart < :reservationEnd " +
            "and r.reservationEnd > :reservationStart " +
            "and s.id in :seatIds " +
            "and r.id <> :id")
    Integer countReservationsInInterval(
            @Param("id") UUID id,
            @Param("reservationStart") LocalDateTime reservationStart,
            @Param("reservationEnd") LocalDateTime reservationEnd,
            @Param("seatIds") List<UUID> seatId
    );

    @Query("select r from ReservationEntity r")
    List<ReservationEntity> findReservations();

    @Query("select r from ReservationEntity r where r.reservedBy.id = :reservedBy")
    List<ReservationEntity> findAllByReservedBy(
            @Param("reservedBy") UUID reservedById
    );

}
