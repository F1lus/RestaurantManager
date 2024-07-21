package org.restaurantmanager.backend.controller.auth;

import lombok.RequiredArgsConstructor;
import org.restaurantmanager.backend.dto.auth.LoginRequest;
import org.restaurantmanager.backend.dto.auth.RegisterRequest;
import org.restaurantmanager.backend.dto.auth.TokenResponse;
import org.restaurantmanager.backend.util.auth.AuthApi;
import org.restaurantmanager.backend.util.auth.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final IAuthService authService;

    @Override
    public ResponseEntity<TokenResponse> login(final LoginRequest loginRequest) {
        return ResponseEntity.ok(
                new TokenResponse(
                        authService.authenticate(loginRequest)
                )
        );
    }

    @Override
    public ResponseEntity<String> register(final RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.ok("");
    }
}
