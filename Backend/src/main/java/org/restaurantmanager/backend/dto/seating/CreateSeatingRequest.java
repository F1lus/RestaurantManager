package org.restaurantmanager.backend.dto.seating;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public final class CreateSeatingRequest {

    @NotNull
    @NotBlank
    private final String name;

    @NotNull
    @Min(1)
    private final Integer personCount;
}
