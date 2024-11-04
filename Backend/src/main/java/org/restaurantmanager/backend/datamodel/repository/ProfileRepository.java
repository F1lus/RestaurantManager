package org.restaurantmanager.backend.datamodel.repository;

import org.restaurantmanager.backend.datamodel.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, UUID> {

    List<ProfileEntity> findAllByIdNotIn(List<UUID> ids);

    Optional<ProfileEntity> findByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    @Query("select case when (count(p) > 0) then true else false end " +
            "from ProfileEntity p " +
            "where p.id <> :id and p.email = :email")
    boolean isEmailTaken(@Param("id") UUID id, @Param("email") String email);

    @Query("select case when (count(p) > 0) then true else false end " +
            "from ProfileEntity p " +
            "where p.id <> :id and p.phoneNumber = :phone")
    boolean isPhoneNumberTaken(@Param("id") UUID id, @Param("phone") String phoneNumber);
}
