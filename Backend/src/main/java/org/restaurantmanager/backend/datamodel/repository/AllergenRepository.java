package org.restaurantmanager.backend.datamodel.repository;

import org.restaurantmanager.backend.datamodel.entity.AllergenEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AllergenRepository extends CrudRepository<AllergenEntity, UUID> {

    @Query("""
            SELECT entity FROM AllergenEntity entity WHERE lower(entity.name) = lower(:name)
            """)
    Optional<AllergenEntity> findByName(@Param("name") String name);

    boolean existsByName(String name);

}
