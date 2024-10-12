package org.restaurantmanager.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.restaurantmanager.backend.datamodel.entity.AllergenEntity;
import org.restaurantmanager.backend.datamodel.entity.FoodEntity;
import org.restaurantmanager.backend.datamodel.repository.FoodRepository;
import org.restaurantmanager.backend.dto.food.CreateFoodRequest;
import org.restaurantmanager.backend.dto.food.Food;
import org.restaurantmanager.backend.dto.food.ModifyFoodRequest;
import org.restaurantmanager.backend.exception.food.FoodNotFoundException;
import org.restaurantmanager.backend.util.allergen.IAllergenService;
import org.restaurantmanager.backend.util.food.FoodConverter;
import org.restaurantmanager.backend.util.food.IFoodService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FoodService implements IFoodService {

    private final IAllergenService allergenService;
    private final FoodRepository foodRepository;

    @Override
    public List<Food> getAllFoods() {
        log.info("Getting all foods...");
        return foodRepository.getAll()
                .stream()
                .map(FoodConverter::toResponse)
                .toList();
    }

    @Override
    public List<FoodEntity> getAllFoodEntities(final List<UUID> ids) {
        return foodRepository.findAllByIdIn(ids);
    }

    @Override
    @Transactional
    public void createFood(final CreateFoodRequest createFoodRequest) {
        log.info("Creating food with name: {}", createFoodRequest.getName());

        val allergenEntities = getAllergens(createFoodRequest.getAllergens());

        val foodEntity = FoodEntity.builder()
                .name(createFoodRequest.getName())
                .description(createFoodRequest.getDescription())
                .price(createFoodRequest.getPrice())
                .allergens(allergenEntities)
                .build();

        foodRepository.save(foodEntity);
    }

    @Override
    @Transactional
    public void modifyFood(final UUID id, final ModifyFoodRequest modifyFoodRequest) {
        val foodEntity = getById(id);

        if (modifyFoodRequest.getName() != null) {
            foodEntity.setName(modifyFoodRequest.getName());
        }

        if (modifyFoodRequest.getDescription() != null) {
            foodEntity.setDescription(modifyFoodRequest.getDescription());
        }

        if (modifyFoodRequest.getPrice() != null) {
            foodEntity.setPrice(modifyFoodRequest.getPrice());
        }

        foodEntity.setAllergens(getAllergens(modifyFoodRequest.getAllergens()));

        foodRepository.save(foodEntity);
    }

    @Override
    @Transactional
    public void deleteFood(final UUID id) {
        foodRepository.deleteById(id);
    }

    private Set<AllergenEntity> getAllergens(final Set<String> allergens) {
        return allergens.stream()
                .map(name -> allergenService.findAllergenByName(name)
                        .orElse(allergenService.createAllergen(name))
                )
                .collect(Collectors.toSet());
    }

    private FoodEntity getById(final UUID id) {
        return foodRepository.findById(id)
                .orElseThrow(FoodNotFoundException::new);
    }
}
