package org.restaurantmanager.backend.util.allergen;

import org.restaurantmanager.backend.datamodel.entity.AllergenEntity;
import org.restaurantmanager.backend.dto.allergen.Allergen;

public final class AllergenConverter {

    private AllergenConverter() {
    }

    public static Allergen toAllergenResponse(final AllergenEntity allergenEntity) {
        return new Allergen(
                allergenEntity.getId(),
                allergenEntity.getName()
        );
    }
}
