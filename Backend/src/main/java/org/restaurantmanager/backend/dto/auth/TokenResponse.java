package org.restaurantmanager.backend.dto.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class TokenResponse {
    private final String token;
}
