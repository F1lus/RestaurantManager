package org.restaurantmanager.backend.datamodel.repository;

import org.restaurantmanager.backend.datamodel.entity.AllergenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AllergenRepository extends CrudRepository<AllergenEntity, UUID> {

    Optional<AllergenEntity> findByNameIgnoreCase(String name);

    boolean existsByName(String name);

}
