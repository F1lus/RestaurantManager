package org.restaurantmanager.backend.datamodel.repository;

import org.restaurantmanager.backend.datamodel.entity.FoodEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FoodRepository extends CrudRepository<FoodEntity, UUID> {

    @Query("select food from FoodEntity food where food.id in (:food_ids)")
    List<FoodEntity> findAllIn(@Param("food_ids") List<UUID> foodIds);

    @Query("select food from FoodEntity food")
    List<FoodEntity> getAll();
}
