package org.restaurantmanager.backend.util.allergen;

import org.restaurantmanager.backend.datamodel.entity.AllergenEntity;

import java.util.Optional;

public interface IAllergenService {

    AllergenEntity createAllergen(String name);

    Optional<AllergenEntity> findAllergenByName(String name);
}
