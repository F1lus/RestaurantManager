package org.restaurantmanager.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.restaurantmanager.backend.datamodel.entity.AllergenEntity;
import org.restaurantmanager.backend.datamodel.entity.FoodEntity;
import org.restaurantmanager.backend.datamodel.repository.FoodRepository;
import org.restaurantmanager.backend.dto.food.CreateFoodRequest;
import org.restaurantmanager.backend.dto.food.ModifyFoodRequest;
import org.restaurantmanager.backend.exception.food.FoodNotFoundException;
import org.restaurantmanager.backend.util.allergen.IAllergenService;
import org.restaurantmanager.backend.util.reservation.IReservationService;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FoodServiceUnitTest {

    private static final String NAME = "Food";
    private static final int PRICE = 100;
    private static final String DESCRIPTION = "Description";


    @Mock
    private IAllergenService allergenService;

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private IReservationService reservationService;

    private FoodService foodService;

    @BeforeEach
    void setup() {
        foodService = new FoodService(
                allergenService,
                foodRepository,
                reservationService
        );
    }

    @Test
    void shouldGetAllFoods() {
        final var allergenEntity = AllergenEntity.builder()
                .id(UUID.randomUUID())
                .name("Gluten")
                .build();
        final var foodEntity = FoodEntity.builder()
                .id(UUID.randomUUID())
                .name(NAME)
                .price(PRICE)
                .description(DESCRIPTION)
                .allergens(Collections.singleton(allergenEntity))
                .build();

        when(foodRepository.getAll()).thenReturn(Collections.singletonList(foodEntity));

        final var result = foodService.getAllFoods();

        assertEquals(1, result.size());
        assertEquals(NAME, result.get(0).getName());
        assertEquals(PRICE, result.get(0).getPrice());
        assertEquals(DESCRIPTION, result.get(0).getDescription());
        assertEquals(1, result.get(0).getAllergens().size());
    }

    @Test
    void createFood() {
        final var createFoodRequest = new CreateFoodRequest(
                NAME,
                DESCRIPTION,
                PRICE,
                Collections.emptySet()
        );

        foodService.createFood(createFoodRequest);

        verify(allergenService, times(0)).findAllergenByName(any(String.class));

        verify(foodRepository, times(1)).save(any(FoodEntity.class));
    }

    @Test
    void createFoodWithNonExistingAllergen() {
        final var allergenName = "Gluten";
        final var createFoodRequest = new CreateFoodRequest(
                NAME,
                DESCRIPTION,
                PRICE,
                Collections.singleton(allergenName)
        );

        when(allergenService.findAllergenByName(allergenName)).thenReturn(Optional.empty());
        when(allergenService.createAllergen(allergenName)).thenReturn(
                AllergenEntity.builder()
                        .id(UUID.randomUUID())
                        .name(NAME)
                        .build()
        );

        foodService.createFood(createFoodRequest);

        verify(allergenService, times(1)).findAllergenByName(allergenName);

        verify(foodRepository, times(1)).save(any(FoodEntity.class));
    }

    @Test
    void createFoodWithExistingAllergen() {
        final var allergenName = "Gluten";
        final var createFoodRequest = new CreateFoodRequest(
                NAME,
                DESCRIPTION,
                PRICE,
                Collections.singleton(allergenName)
        );
        final var allergenEntity = AllergenEntity.builder()
                .id(UUID.randomUUID())
                .name(NAME)
                .build();

        when(allergenService.findAllergenByName(allergenName)).thenReturn(Optional.of(allergenEntity));

        foodService.createFood(createFoodRequest);

        verify(allergenService, times(1)).findAllergenByName(allergenName);

        verify(foodRepository, times(1)).save(any(FoodEntity.class));
    }

    @Test
    void cancelUpdateIfNoFoodIsFound() {
        final var id = UUID.randomUUID();
        final var modifyFoodRequest = new ModifyFoodRequest(
                null,
                null,
                null,
                Collections.emptySet()
        );

        when(foodRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(
                FoodNotFoundException.class,
                () -> foodService.modifyFood(id, modifyFoodRequest)
        );
    }

    @Test
    void updateFoodWithNullValues() {
        final var id = UUID.randomUUID();
        final var modifyFoodRequest = new ModifyFoodRequest(
                null,
                null,
                null,
                Collections.emptySet()
        );
        final var allergenEntity = AllergenEntity.builder()
                .id(UUID.randomUUID())
                .name("Gluten")
                .build();
        final var expectedFoodEntity = FoodEntity.builder()
                .id(id)
                .name(NAME)
                .price(PRICE)
                .description(DESCRIPTION)
                .allergens(Collections.singleton(allergenEntity))
                .build();

        when(foodRepository.findById(id)).thenReturn(Optional.of(expectedFoodEntity));

        assertDoesNotThrow(() -> foodService.modifyFood(id, modifyFoodRequest));

        verify(allergenService, times(0)).findAllergenByName(any(String.class));
        verify(foodRepository, times(1)).save(expectedFoodEntity);
    }

    @Test
    void updateFoodWithNonNullValues() {
        final var id = UUID.randomUUID();
        final var modifiedName = "Modified name";
        final var modifiedPrice = 200;
        final var modifiedDescription = "Modified description";
        final var allergenName = "Gluten";

        final var allergenEntity = AllergenEntity.builder()
                .id(UUID.randomUUID())
                .name("Gluten")
                .build();
        final var modifyFoodRequest = new ModifyFoodRequest(
                modifiedName,
                modifiedDescription,
                modifiedPrice,
                Collections.singleton(allergenName)
        );
        final var foodEntity = FoodEntity.builder()
                .id(id)
                .name(NAME)
                .price(PRICE)
                .description(DESCRIPTION)
                .allergens(Collections.emptySet())
                .build();
        final var expectedFoodEntity = FoodEntity.builder()
                .id(id)
                .name(modifiedName)
                .price(modifiedPrice)
                .description(modifiedDescription)
                .allergens(Collections.singleton(allergenEntity))
                .build();

        when(foodRepository.findById(id)).thenReturn(Optional.of(foodEntity));

        assertDoesNotThrow(() -> foodService.modifyFood(id, modifyFoodRequest));

        verify(allergenService, times(1)).findAllergenByName(any(String.class));
        verify(foodRepository, times(1)).save(expectedFoodEntity);
    }

    @Test
    void shouldNotDeleteFoodWhenNotExists() {
        final var id = UUID.randomUUID();

        when(foodRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(FoodNotFoundException.class, () -> foodService.deleteFood(id));
    }

    @Test
    void deleteFoodIfExists() {
        final var id = UUID.randomUUID();

        when(foodRepository.findById(id)).thenReturn(
                Optional.of(
                        FoodEntity.builder()
                                .id(id)
                                .build()
                )
        );

        assertDoesNotThrow(() -> foodService.deleteFood(id));

        verify(foodRepository, times(1)).deleteById(id);
    }
}
