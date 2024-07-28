package org.restaurantmanager.backend.util.allergen;

import org.restaurantmanager.backend.datamodel.entity.AllergenEntity;
import org.restaurantmanager.backend.dto.allergen.AllergenResponse;

public final class AllergenConverter {

    private AllergenConverter() {
    }

    public static AllergenResponse toAllergenResponse(final AllergenEntity allergenEntity) {
        return new AllergenResponse(
                allergenEntity.getId(),
                allergenEntity.getName()
        );
    }
}
