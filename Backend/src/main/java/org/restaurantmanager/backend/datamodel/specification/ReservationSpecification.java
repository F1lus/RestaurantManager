package org.restaurantmanager.backend.datamodel.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.restaurantmanager.backend.datamodel.entity.FoodEntity;
import org.restaurantmanager.backend.datamodel.entity.ProfileEntity;
import org.restaurantmanager.backend.datamodel.entity.ReservationEntity;
import org.restaurantmanager.backend.datamodel.entity.SeatingEntity;
import org.restaurantmanager.backend.util.reservation.ReservationFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ReservationSpecification implements Specification<ReservationEntity> {

    private final transient ReservationFilter reservationFilter;

    @Override
    public Predicate toPredicate(
            @NonNull Root<ReservationEntity> root,
            @NonNull CriteriaQuery<?> query,
            @NonNull CriteriaBuilder criteriaBuilder
    ) {
        List<Predicate> predicates = buildPredicates(
                reservationIdPredicate(root, criteriaBuilder),
                seatingIdPredicate(root, criteriaBuilder),
                profileIdPredicate(root, criteriaBuilder),
                foodIdsPredicate(root, criteriaBuilder),
                reservationStartPredicate(root, criteriaBuilder),
                reservationEndPredicate(root, criteriaBuilder)
        );

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate reservationIdPredicate(Root<ReservationEntity> root, CriteriaBuilder criteriaBuilder) {
        if (reservationFilter.getReservationId() == null) {
            return null;
        }

        return criteriaBuilder.equal(
                root.get("id"),
                reservationFilter.getReservationId()
        );
    }

    private Predicate seatingIdPredicate(Root<ReservationEntity> root, CriteriaBuilder criteriaBuilder) {
        if (reservationFilter.getSeatingId() == null) {
            return null;
        }

        Join<ReservationEntity, SeatingEntity> seatingJoin = root.join("seat_id");
        return criteriaBuilder.equal(
                seatingJoin.get("id"),
                reservationFilter.getSeatingId()
        );
    }

    private Predicate profileIdPredicate(Root<ReservationEntity> root, CriteriaBuilder criteriaBuilder) {
        if (reservationFilter.getProfileId() == null) {
            return null;
        }

        Join<ReservationEntity, ProfileEntity> profileJoin = root.join("reserved_by_profile_id");
        return criteriaBuilder.equal(
                profileJoin.get("id"),
                reservationFilter.getProfileId()
        );
    }

    private Predicate foodIdsPredicate(Root<ReservationEntity> root, CriteriaBuilder criteriaBuilder) {
        if (CollectionUtils.isEmpty(reservationFilter.getFoodIds())) {
            return null;
        }

        Join<ReservationEntity, FoodEntity> foodJoin = root.join("food_id");
        return criteriaBuilder.in(
                foodJoin.get("id").in(reservationFilter.getFoodIds())
        );
    }

    private Predicate reservationStartPredicate(Root<ReservationEntity> root, CriteriaBuilder criteriaBuilder) {
        if (reservationFilter.getReservationStart() == null) {
            return null;
        }

        return criteriaBuilder.greaterThanOrEqualTo(
                root.get("reservation_start"),
                reservationFilter.getReservationStart()
        );
    }

    private Predicate reservationEndPredicate(Root<ReservationEntity> root, CriteriaBuilder criteriaBuilder) {
        if (reservationFilter.getReservationEnd() == null) {
            return null;
        }

        return criteriaBuilder.greaterThanOrEqualTo(
                root.get("reservation_end"),
                reservationFilter.getReservationEnd()
        );
    }

    private List<Predicate> buildPredicates(Predicate... predicates) {
        return Arrays.stream(predicates)
                .filter(Objects::nonNull)
                .toList();
    }
}
