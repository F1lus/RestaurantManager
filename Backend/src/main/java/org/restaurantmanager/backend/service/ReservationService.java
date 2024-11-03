package org.restaurantmanager.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.restaurantmanager.backend.datamodel.entity.FoodEntity;
import org.restaurantmanager.backend.datamodel.entity.ReservationEntity;
import org.restaurantmanager.backend.datamodel.fieldtype.ProfileType;
import org.restaurantmanager.backend.datamodel.repository.FoodRepository;
import org.restaurantmanager.backend.datamodel.repository.ReservationRepository;
import org.restaurantmanager.backend.dto.reservation.CreateReservationRequest;
import org.restaurantmanager.backend.dto.reservation.ModifyReservationRequest;
import org.restaurantmanager.backend.dto.reservation.Reservation;
import org.restaurantmanager.backend.exception.reservation.ReservationConflictException;
import org.restaurantmanager.backend.exception.reservation.ReservationNotFoundException;
import org.restaurantmanager.backend.exception.reservation.ReservationTimeInvalidException;
import org.restaurantmanager.backend.util.profile.IProfileService;
import org.restaurantmanager.backend.util.reservation.IReservationService;
import org.restaurantmanager.backend.util.reservation.ReservationConverter;
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

    private final ISeatingService seatingService;
    private final IProfileService profileService;
    private final ReservationRepository reservationRepository;
    private final FoodRepository foodRepository;

    @Override
    public List<Reservation> getReservations() {
        log.info("Getting reservations...");
        val currentDate = LocalDateTime.now();
        val profileEntity = profileService.getCurrentUser();
        if (profileEntity.getProfileType() == ProfileType.USER) {
            return reservationRepository.findAllByReservedBy(profileEntity.getId(), currentDate)
                    .stream()
                    .map(ReservationConverter::toResponse)
                    .toList();
        }

        return reservationRepository.findReservations(currentDate)
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
                createReservationRequest.getSeatIds()
        );

        val foodEntities = getFoodEntities(createReservationRequest.getFoodIds());
        val seatingEntities = seatingService.getSeatsByIds(createReservationRequest.getSeatIds());
        val profileEntity = profileService.getCurrentUser();

        val reservationEntity = ReservationEntity.builder()
                .seatingEntities(seatingEntities)
                .foods(foodEntities)
                .reservedBy(profileEntity)
                .reservationStart(createReservationRequest.getReservationStart())
                .reservationEnd(createReservationRequest.getReservationEnd())
                .build();

        reservationRepository.save(reservationEntity);
    }

    @Override
    public void modifyReservation(final UUID id, final ModifyReservationRequest modifyReservationRequest) {
        validateReservationTime(
                id,
                modifyReservationRequest.getReservationStart(),
                modifyReservationRequest.getReservationEnd(),
                modifyReservationRequest.getSeatIds()
        );

        val reservationEntity = getReservationEntityById(id);
        val foodEntities = getFoodEntities(modifyReservationRequest.getFoodIds());
        val seatingEntities = seatingService.getSeatsByIds(modifyReservationRequest.getSeatIds());

        reservationEntity.setFoods(foodEntities);
        reservationEntity.setSeatingEntities(seatingEntities);
        reservationEntity.setReservationStart(modifyReservationRequest.getReservationStart());
        reservationEntity.setReservationEnd(modifyReservationRequest.getReservationEnd());

        reservationRepository.save(reservationEntity);
    }

    @Override
    public void deleteReservation(final UUID id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void handleFoodDeletion(final FoodEntity foodEntity) {
        foodEntity.getReservations().forEach(reservation -> reservation.getFoods().remove(foodEntity));
        reservationRepository.saveAll(foodEntity.getReservations());
    }

    private ReservationEntity getReservationEntityById(final UUID id) {
        return reservationRepository.findById(id)
                .orElseThrow(ReservationNotFoundException::new);
    }

    private List<FoodEntity> getFoodEntities(final List<UUID> foodIds) {
        if (CollectionUtils.isEmpty(foodIds)) {
            return Collections.emptyList();
        }

        return foodRepository.findAllByIdIn(foodIds);
    }

    private void validateReservationTime(
            final UUID id,
            final LocalDateTime reservationStart,
            final LocalDateTime reservationEnd,
            final List<UUID> seatIds
    ) {
        log.info(
                "Checking if the reservation {} can be made from {} to {}",
                id,
                reservationStart,
                reservationEnd
        );

        if (isReservationValid(reservationStart, reservationEnd)) {
            throw new ReservationTimeInvalidException();
        }

        val reservationInInterval = reservationRepository.countReservationsInInterval(
                id,
                reservationStart,
                reservationEnd,
                seatIds
        );

        if (reservationInInterval != 0) {
            throw new ReservationConflictException();
        }
    }

    private void validateReservationTime(
            final LocalDateTime reservationStart,
            final LocalDateTime reservationEnd,
            final List<UUID> seatIds
    ) {
        log.info(
                "Checking if the reservation modification can be made from {} to {}",
                reservationStart,
                reservationEnd
        );

        if (isReservationValid(reservationStart, reservationEnd)) {
            throw new ReservationTimeInvalidException();
        }

        val reservationInInterval = reservationRepository.countReservationsInInterval(
                reservationStart,
                reservationEnd,
                seatIds
        );

        if (reservationInInterval != 0) {
            throw new ReservationConflictException();
        }
    }

    private boolean isReservationValid(final LocalDateTime reservationStart, final LocalDateTime reservationEnd) {
        return reservationEnd.isBefore(reservationStart)
                || reservationStart.isAfter(reservationEnd)
                || reservationEnd.isEqual(reservationStart)
                || reservationStart.plusMinutes(30).isAfter(reservationEnd)
                || (reservationStart.plusHours(4).isBefore(reservationEnd)
                && !reservationStart.plusHours(4).isEqual(reservationEnd));
    }
}
