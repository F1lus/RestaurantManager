package org.restaurantmanager.backend.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.restaurantmanager.backend.datamodel.entity.AllergenEntity;
import org.restaurantmanager.backend.datamodel.repository.AllergenRepository;
import org.restaurantmanager.backend.exception.allergen.AllergenConstraintViolationException;
import org.restaurantmanager.backend.util.allergen.IAllergenService;
import org.restaurantmanager.backend.util.exception.ApplicationError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AllergenService implements IAllergenService {

    private final AllergenRepository allergenRepository;

    @Override
    @Transactional
    public AllergenEntity createAllergen(final String name) {
        log.info("Creating allergen with name: {}", name);
        validateName(name);

        val allergen = AllergenEntity.builder()
                .name(name)
                .build();

        return allergenRepository.save(allergen);
    }

    @Override
    public Optional<AllergenEntity> findAllergenByName(final String name) {
        log.info("Finding allergen by name: {}", name);
        return allergenRepository.findByNameIgnoreCase(name);
    }

    private void validateName(final String name) {
        log.info("Checking if allergen name is unique: {}", name);
        val isNameTaken = allergenRepository.existsByName(name);
        if (isNameTaken) {
            throw new AllergenConstraintViolationException(ApplicationError.ALLERGEN_NAME_DUPLICATE);
        }
    }
}
