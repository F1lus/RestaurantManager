package org.restaurantmanager.backend.util.auth;

import org.restaurantmanager.backend.dto.auth.LoginRequest;
import org.restaurantmanager.backend.dto.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.restaurantmanager.backend.util.auth.AuthApi.AUTH_PATH;

@RequestMapping(AUTH_PATH)
public interface AuthApi {

    String AUTH_PATH = "/api/auth";
    String LOGIN_PATH = "/login";
    String REGISTER_PATH = "/register";

    @PostMapping(LOGIN_PATH)
    ResponseEntity<String> login(@RequestBody final LoginRequest loginRequest);

    @PostMapping(REGISTER_PATH)
    ResponseEntity<String> register(@RequestBody final RegisterRequest registerRequest);
}
