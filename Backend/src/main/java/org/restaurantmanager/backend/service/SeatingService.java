package org.restaurantmanager.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.restaurantmanager.backend.datamodel.entity.SeatingEntity;
import org.restaurantmanager.backend.datamodel.repository.SeatingRepository;
import org.restaurantmanager.backend.dto.seating.CreateSeatingRequest;
import org.restaurantmanager.backend.dto.seating.ModifySeatingRequest;
import org.restaurantmanager.backend.dto.seating.Seating;
import org.restaurantmanager.backend.exception.seating.SeatingNameViolationException;
import org.restaurantmanager.backend.exception.seating.SeatingNotFoundException;
import org.restaurantmanager.backend.util.seat.ISeatingService;
import org.restaurantmanager.backend.util.seat.SeatingConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SeatingService implements ISeatingService {

    private final SeatingRepository seatingRepository;

    @Override
    public List<Seating> getSeats() {
        return seatingRepository.getAll()
                .stream()
                .map(SeatingConverter::toResponse)
                .toList();
    }

    @Override
    public List<SeatingEntity> getSeatsByIds(final List<UUID> ids) {
        return seatingRepository.getAllByIds(ids);
    }

    @Override
    public void addSeat(final CreateSeatingRequest createSeatingRequest) {
        validateName(createSeatingRequest.getName());

        val seatingEntity = SeatingConverter.toEntity(createSeatingRequest);
        seatingRepository.save(seatingEntity);
    }

    @Override
    public void modifySeat(final UUID id, final ModifySeatingRequest modifySeatingRequest) {
        val seatingEntity = getById(id);

        if (modifySeatingRequest.getName() != null) {
            validateName(modifySeatingRequest.getName(), id);
            seatingEntity.setName(modifySeatingRequest.getName());
        }

        if (modifySeatingRequest.getPersonCount() != null) {
            seatingEntity.setPersonCount(modifySeatingRequest.getPersonCount());
        }

        seatingRepository.save(seatingEntity);
    }

    @Override
    public void deleteSeat(final UUID id) {
        seatingRepository.deleteById(id);
    }

    private void validateName(final String name) {
        val isNameTaken = seatingRepository.existsByName(name);
        if (isNameTaken) {
            throw new SeatingNameViolationException();
        }
    }

    private void validateName(final String name, final UUID id) {
        val seatingEntityOptional = seatingRepository.findByName(name);
        if (seatingEntityOptional.isEmpty()) {
            return;
        }
        val seatingEntity = seatingEntityOptional.get();
        if (seatingEntity.getId().equals(id)) {
            return;
        }

        throw new SeatingNameViolationException();
    }

    private SeatingEntity getById(final UUID id) {
        return seatingRepository.findById(id)
                .orElseThrow(SeatingNotFoundException::new);
    }
}
