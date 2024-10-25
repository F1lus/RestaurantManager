package org.restaurantmanager.backend.datamodel.repository;

import org.restaurantmanager.backend.datamodel.entity.AllergenEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AllergenRepository extends CrudRepository<AllergenEntity, UUID> {

    @Query("select e from AllergenEntity e")
    List<AllergenEntity> getAll();

    Optional<AllergenEntity> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);

}
