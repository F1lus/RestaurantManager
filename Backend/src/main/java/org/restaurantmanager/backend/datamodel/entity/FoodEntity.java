package org.restaurantmanager.backend.datamodel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Table(name = "food")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private Integer price;

    @ManyToMany
    @JoinTable(
            name = "foods_allergens",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id")

    )
    private Set<AllergenEntity> allergens;

    @ManyToMany(mappedBy = "foods")
    private List<ReservationEntity> reservations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
