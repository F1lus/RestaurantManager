package org.restaurantmanager.backend.datamodel.repository;

import org.restaurantmanager.backend.datamodel.entity.SeatingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SeatingRepository extends CrudRepository<SeatingEntity, UUID> {

    boolean existsByName(String name);

    Optional<SeatingEntity> findByName(String name);

    @Query("select seat from SeatingEntity seat")
    List<SeatingEntity> getAll();

    @Query("select seat from SeatingEntity seat where seat.id in :ids")
    List<SeatingEntity> getAllByIds(@Param("ids") List<UUID> ids);
}
