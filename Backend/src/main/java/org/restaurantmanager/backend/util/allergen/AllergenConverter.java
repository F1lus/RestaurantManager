package org.restaurantmanager.backend.util.allergen;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.restaurantmanager.backend.datamodel.entity.AllergenEntity;
import org.restaurantmanager.backend.dto.allergen.Allergen;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AllergenConverter {

    public static Allergen toResponse(final AllergenEntity allergenEntity) {
        return Allergen.builder()
                .id(allergenEntity.getId())
                .name(allergenEntity.getName())
                .build();
    }

    public static Set<Allergen> toResponse(final Collection<AllergenEntity> allergenEntities) {
        return allergenEntities.stream()
                .map(AllergenConverter::toResponse)
                .collect(Collectors.toSet());
    }
}
