package org.restaurantmanager.backend.datamodel.repository;

import org.restaurantmanager.backend.datamodel.entity.FoodEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FoodRepository extends CrudRepository<FoodEntity, UUID> {

    List<FoodEntity> findAllByIdIn(List<UUID> foodIds);

    @Query("select food from FoodEntity food")
    List<FoodEntity> getAll();
}
