package org.restaurantmanager.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.restaurantmanager.backend.datamodel.entity.AllergenEntity;
import org.restaurantmanager.backend.datamodel.entity.FoodEntity;
import org.restaurantmanager.backend.datamodel.repository.AllergenRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AllergenServiceUnitTest {

    private static final String NAME = "Gluten";

    @Mock
    private AllergenRepository allergenRepository;

    private AllergenService allergenService;

    @BeforeEach
    void setup() {
        allergenService = new AllergenService(allergenRepository);
    }

    @Test
    void shouldGetAllAllergens() {
        final var allergenEntity = AllergenEntity.builder()
                .name(NAME)
                .id(UUID.randomUUID())
                .foods(Collections.emptySet())
                .build();
        when(allergenRepository.getAll()).thenReturn(Collections.singletonList(allergenEntity));

        final var allergens = allergenService.getAllAllergens();

        assertEquals(1, allergens.size());
        assertEquals(allergenEntity.getId(), allergens.get(0).getId());
        assertEquals(allergenEntity.getName(), allergens.get(0).getName());
    }

    @Test
    void shouldReturnAllergenIfExists() {
        final var allergenEntity = AllergenEntity.builder()
                .name(NAME)
                .id(UUID.randomUUID())
                .foods(Collections.emptySet())
                .build();
        when(allergenRepository.existsByNameIgnoreCase(NAME)).thenReturn(true);
        when(allergenRepository.findByNameIgnoreCase(NAME)).thenReturn(Optional.of(allergenEntity));

        assertDoesNotThrow(() -> allergenService.createAllergen(NAME));

        verify(allergenRepository, times(0)).save(any(AllergenEntity.class));
    }

    @Test
    void shouldCreateNewAllergenIfDoesNotExists() {
        when(allergenRepository.existsByNameIgnoreCase(NAME)).thenReturn(false);

        assertDoesNotThrow(() -> allergenService.createAllergen(NAME));

        verify(allergenRepository, times(1)).save(any(AllergenEntity.class));
    }

    @Test
    void shouldFindAllergenByName() {
        when(allergenRepository.findByNameIgnoreCase(NAME)).thenReturn(Optional.of(
                AllergenEntity.builder().build()
        ));

        final var allergen = allergenService.findAllergenByName(NAME);

        assertTrue(allergen.isPresent());
    }

    @Test
    void doNotDeleteAllergenIfFoodHasNone() {
        final var foodEntity = FoodEntity.builder()
                .name("Food")
                .allergens(Collections.emptySet())
                .build();

        allergenService.foodDeletionHandler(foodEntity);

        verify(allergenRepository, times(0)).deleteAll(any());
    }

    @Test
    void deleteAllergenIfFoodHasAnyAndAllergenIsOrphan() {
        final var allergenEntity = AllergenEntity.builder()
                .name(NAME)
                .foods(Collections.emptySet())
                .build();
        final var foodEntity = FoodEntity.builder()
                .name("Food")
                .allergens(Collections.emptySet())
                .build();
        final var allergenSet = new HashSet<AllergenEntity>();
        final var foodSet = new HashSet<FoodEntity>();

        allergenSet.add(allergenEntity);
        foodSet.add(foodEntity);

        foodEntity.setAllergens(allergenSet);
        allergenEntity.setFoods(foodSet);

        allergenService.foodDeletionHandler(foodEntity);

        verify(allergenRepository, times(1)).deleteAll(any());
    }
}
