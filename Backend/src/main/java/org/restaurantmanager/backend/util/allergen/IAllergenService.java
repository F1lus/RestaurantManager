package org.restaurantmanager.backend.util.allergen;

import org.restaurantmanager.backend.datamodel.entity.AllergenEntity;

import java.util.Optional;

public interface IAllergenService {

    AllergenEntity createAllergen(final String name);

    Optional<AllergenEntity> findAllergenByName(final String name);
}
