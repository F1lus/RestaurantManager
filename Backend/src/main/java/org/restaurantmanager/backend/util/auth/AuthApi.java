package org.restaurantmanager.backend.util.auth;

import jakarta.validation.Valid;
import org.restaurantmanager.backend.dto.auth.LoginRequest;
import org.restaurantmanager.backend.dto.auth.RegisterRequest;
import org.restaurantmanager.backend.dto.auth.TokenResponse;
import org.restaurantmanager.backend.dto.profile.GeneralProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static org.restaurantmanager.backend.util.auth.AuthApi.AUTH_PATH;

@RequestMapping(AUTH_PATH)
public interface AuthApi {

    String AUTH_PATH = "/api/auth";
    String LOGIN_PATH = "/login";
    String REGISTER_PATH = "/register";

    @PostMapping(LOGIN_PATH)
    ResponseEntity<TokenResponse> login(@Valid @RequestBody final LoginRequest loginRequest);

    @PostMapping(REGISTER_PATH)
    ResponseEntity<String> register(@Valid @RequestBody final RegisterRequest registerRequest);

    @GetMapping
    ResponseEntity<GeneralProfile> getCurrentUser(final Principal principal);
}
