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
    public SeatingEntity getSeatById(final UUID id) {
        return seatingRepository.findById(id).orElseThrow(SeatingNotFoundException::new);
    }

    @Override
    @Transactional
    public void addSeat(final CreateSeatingRequest createSeatingRequest) {
        validateName(createSeatingRequest.getName());

        val seatingEntity = SeatingConverter.toEntity(createSeatingRequest);
        seatingRepository.save(seatingEntity);
    }

    @Override
    @Transactional
    public void modifySeat(final UUID id, final ModifySeatingRequest modifySeatingRequest) {
        val seatingEntity = getById(id);
        boolean entityWasModified = false;

        if (modifySeatingRequest.getName() != null) {
            validateName(modifySeatingRequest.getName());
            seatingEntity.setName(modifySeatingRequest.getName());
            entityWasModified = true;
        }

        if (modifySeatingRequest.getPersonCount() != null) {
            seatingEntity.setPersonCount(modifySeatingRequest.getPersonCount());
            entityWasModified = true;
        }

        if (entityWasModified) {
            seatingRepository.save(seatingEntity);
        }
    }

    @Override
    @Transactional
    public void deleteSeat(final UUID id) {
        seatingRepository.deleteById(id);
    }

    private void validateName(final String name) {
        val isNameTaken = seatingRepository.existsByName(name);
        if (isNameTaken) {
            throw new SeatingNameViolationException();
        }
    }

    private SeatingEntity getById(final UUID id) {
        return seatingRepository.findById(id)
                .orElseThrow(SeatingNotFoundException::new);
    }
}
