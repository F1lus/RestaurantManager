package org.restaurantmanager.backend.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.restaurantmanager.backend.datamodel.entity.AllergenEntity;
import org.restaurantmanager.backend.datamodel.entity.FoodEntity;
import org.restaurantmanager.backend.datamodel.repository.AllergenRepository;
import org.restaurantmanager.backend.dto.allergen.Allergen;
import org.restaurantmanager.backend.exception.allergen.AllergenConstraintViolationException;
import org.restaurantmanager.backend.util.allergen.AllergenConverter;
import org.restaurantmanager.backend.util.allergen.IAllergenService;
import org.restaurantmanager.backend.util.exception.ApplicationError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AllergenService implements IAllergenService {

    private final AllergenRepository allergenRepository;

    @Override
    public List<Allergen> getAllAllergens() {
        return allergenRepository.getAll()
                .stream()
                .map(AllergenConverter::toResponse)
                .toList();
    }

    @Override
    public AllergenEntity createAllergen(final String name) {
        log.info("Creating allergen with name: {}", name);
        try {
            validateName(name);

            val allergenEntity = AllergenEntity.builder()
                    .name(name)
                    .build();

            return allergenRepository.save(allergenEntity);
        } catch (AllergenConstraintViolationException e) {
            return allergenRepository.findByNameIgnoreCase(name).orElseThrow();
        }
    }

    @Override
    public Optional<AllergenEntity> findAllergenByName(final String name) {
        log.info("Finding allergen by name: {}", name);

        return allergenRepository.findByNameIgnoreCase(name);
    }

    @Override
    public void foodDeletionHandler(final FoodEntity foodEntity) {
        foodEntity.getAllergens().forEach(allergen -> allergen.getFoods().remove(foodEntity));

        val emptyAllergens = foodEntity.getAllergens().stream()
                .filter(allergen -> allergen.getFoods().isEmpty())
                .toList();
        if (!emptyAllergens.isEmpty()) {
            allergenRepository.deleteAll(emptyAllergens);
        }
    }

    private void validateName(final String name) {
        log.info("Checking if allergen name is unique: {}", name);
        val isNameTaken = allergenRepository.existsByNameIgnoreCase(name);
        if (isNameTaken) {
            log.info("Allergen already exists, skipping...");
            throw new AllergenConstraintViolationException(ApplicationError.ALLERGEN_NAME_DUPLICATE);
        }
    }
}
