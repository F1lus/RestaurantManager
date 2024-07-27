package org.restaurantmanager.backend.dto.auth;

import lombok.Data;

@Data
public final class TokenResponse {
    private final String token;
}
