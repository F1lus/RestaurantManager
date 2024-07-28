package org.restaurantmanager.backend.dto.profile;

import lombok.Builder;

import java.util.UUID;

@Builder
public final class GeneralProfile {
    private final UUID id;
    private final String fullName;
    private final String email;
    private final String phoneNumber;
}
