package org.restaurantmanager.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.restaurantmanager.backend.datamodel.entity.FoodEntity;
import org.restaurantmanager.backend.datamodel.entity.ReservationEntity;
import org.restaurantmanager.backend.datamodel.repository.ReservationRepository;
import org.restaurantmanager.backend.datamodel.specification.ReservationSpecification;
import org.restaurantmanager.backend.dto.reservation.CreateReservationRequest;
import org.restaurantmanager.backend.dto.reservation.ModifyReservationRequest;
import org.restaurantmanager.backend.dto.reservation.Reservation;
import org.restaurantmanager.backend.exception.reservation.ReservationConflictException;
import org.restaurantmanager.backend.exception.reservation.ReservationNotFoundException;
import org.restaurantmanager.backend.exception.reservation.ReservationTimeInvalidException;
import org.restaurantmanager.backend.util.food.IFoodService;
import org.restaurantmanager.backend.util.profile.IProfileService;
import org.restaurantmanager.backend.util.reservation.IReservationService;
import org.restaurantmanager.backend.util.reservation.ReservationConverter;
import org.restaurantmanager.backend.util.reservation.ReservationFilter;
import org.restaurantmanager.backend.util.seat.ISeatingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReservationService implements IReservationService {

    private final IFoodService foodService;
    private final ISeatingService seatingService;
    private final IProfileService profileService;
    private final ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getReservations(final ReservationFilter reservationFilter) {
        log.info("Getting reservations by the filter criteria");
        val reservationSpecification = new ReservationSpecification(reservationFilter);
        return reservationRepository.findAll(reservationSpecification)
                .stream()
                .map(ReservationConverter::toResponse)
                .toList();
    }

    @Override
    public void createReservation(final CreateReservationRequest createReservationRequest) {
        log.info("Creating reservation...");
        validateReservationTime(
                createReservationRequest.getReservationStart(),
                createReservationRequest.getReservationEnd(),
                createReservationRequest.getSeatingId()
        );

        val foodEntities = getFoodEntities(createReservationRequest.getFoodIds());
        val seatingEntity = seatingService.getSeatById(createReservationRequest.getSeatingId());
        val profileEntity = profileService.getCurrentUser();

        val reservationEntity = ReservationEntity.builder()
                .seatingEntity(seatingEntity)
                .foods(foodEntities)
                .reservedBy(profileEntity)
                .reservationStart(createReservationRequest.getReservationStart())
                .reservationEnd(createReservationRequest.getReservationEnd())
                .build();

        reservationRepository.save(reservationEntity);
    }

    @Override
    public void modifyReservation(final ModifyReservationRequest modifyReservationRequest) {
        validateReservationTime(
                modifyReservationRequest.getReservationStart(),
                modifyReservationRequest.getReservationEnd(),
                modifyReservationRequest.getSeatingId()
        );

        val reservationEntity = getReservationEntityById(modifyReservationRequest.getId());
        val foodEntities = getFoodEntities(modifyReservationRequest.getFoodIds());
        val seatingEntity = seatingService.getSeatById(modifyReservationRequest.getSeatingId());

        reservationEntity.setFoods(foodEntities);
        reservationEntity.setSeatingEntity(seatingEntity);
        reservationEntity.setReservationStart(modifyReservationRequest.getReservationStart());
        reservationEntity.setReservationEnd(modifyReservationRequest.getReservationEnd());

        reservationRepository.save(reservationEntity);
    }

    @Override
    public void deleteReservation(final UUID id) {
        reservationRepository.deleteById(id);
    }

    private ReservationEntity getReservationEntityById(final UUID id) {
        return reservationRepository.findById(id)
                .orElseThrow(ReservationNotFoundException::new);
    }

    private List<FoodEntity> getFoodEntities(final List<UUID> foodIds) {
        if (CollectionUtils.isEmpty(foodIds)) {
            return Collections.emptyList();
        }

        return foodService.getAllFoodEntities(foodIds);
    }

    private void validateReservationTime(
            final LocalDateTime reservationStart,
            final LocalDateTime reservationEnd,
            final UUID seatId
    ) {
        log.info(
                "Checking if the reservation can be made for the seat {} from {} to {}",
                seatId,
                reservationStart,
                reservationEnd
        );

        if (reservationEnd.isBefore(reservationStart) || reservationStart.isAfter(reservationEnd)
                || reservationEnd.isEqual(reservationStart)
        ) {
            throw new ReservationTimeInvalidException();
        }

        val reservationInInterval = reservationRepository.countReservationsInInterval(
                reservationStart,
                reservationEnd,
                seatId
        );

        if (reservationInInterval != 0) {
            throw new ReservationConflictException();
        }
    }
}
