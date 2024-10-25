package org.restaurantmanager.backend.util.allergen;

import org.restaurantmanager.backend.datamodel.entity.AllergenEntity;
import org.restaurantmanager.backend.dto.allergen.Allergen;

import java.util.List;
import java.util.Optional;

public interface IAllergenService {

    List<Allergen> getAllAllergens();

    AllergenEntity createAllergen(final String name);

    Optional<AllergenEntity> findAllergenByName(final String name);
}
