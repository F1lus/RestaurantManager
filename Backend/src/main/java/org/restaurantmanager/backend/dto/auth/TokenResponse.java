package org.restaurantmanager.backend.dto.auth;

import lombok.Data;

@Data
public class TokenResponse {
    private final String token;
}
