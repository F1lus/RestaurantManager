package org.restaurantmanager.backend.datamodel.repository;

import org.restaurantmanager.backend.datamodel.entity.SeatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SeatingRepository extends JpaRepository<SeatingEntity, UUID> {
}
