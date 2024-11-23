package org.restaurantmanager.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.restaurantmanager.backend.config.authentication.JwtService;
import org.restaurantmanager.backend.datamodel.entity.ProfileEntity;
import org.restaurantmanager.backend.datamodel.repository.ProfileRepository;
import org.restaurantmanager.backend.dto.auth.LoginRequest;
import org.restaurantmanager.backend.dto.auth.RegisterRequest;
import org.restaurantmanager.backend.exception.auth.IncorrectCredentialsException;
import org.restaurantmanager.backend.exception.auth.PasswordConfirmException;
import org.restaurantmanager.backend.exception.profile.ProfileEmailViolationException;
import org.restaurantmanager.backend.exception.profile.ProfilePhoneNumberViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceUnitTest {

    private static final String EMAIL = "filimon.mark@gmail.com";
    private static final String PASSWORD = "Abc_123!";
    private static final String FULL_NAME = "John Doe";
    private static final String PHONE_NUMBER = "123456789";

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    private AuthService authService;

    @BeforeEach
    void setup() {
        authService = new AuthService(
                profileRepository,
                passwordEncoder,
                authenticationManager,
                jwtService
        );
    }

    @Test
    void denyLoginWithoutAccount() {
        final var loginRequest = new LoginRequest(
                EMAIL,
                PASSWORD
        );

        when(profileRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());

        assertThrows(
                IncorrectCredentialsException.class,
                () -> authService.authenticate(loginRequest)
        );
    }

    @Test
    void acceptLoginWithAccount() {
        final var loginRequest = new LoginRequest(
                EMAIL,
                PASSWORD
        );

        when(profileRepository.findByEmail(EMAIL)).thenReturn(Optional.of(
                ProfileEntity.builder()
                        .email(EMAIL)
                        .build()
        ));

        assertDoesNotThrow(() -> authService.authenticate(loginRequest));
    }

    @Test
    void cancelRegisterWhenPasswordsDoNotMatch() {
        final var registerRequest = new RegisterRequest(
                EMAIL,
                FULL_NAME,
                PASSWORD,
                "Abc_123!1",
                PHONE_NUMBER
        );

        assertThrows(
                PasswordConfirmException.class,
                () -> authService.register(registerRequest)
        );
    }

    @Test
    void cancelRegisterWhenEmailIsTaken() {
        final var registerRequest = new RegisterRequest(
                EMAIL,
                FULL_NAME,
                PASSWORD,
                PASSWORD,
                PHONE_NUMBER
        );

        when(profileRepository.existsByEmail(EMAIL)).thenReturn(true);

        assertThrows(
                ProfileEmailViolationException.class,
                () -> authService.register(registerRequest)
        );
    }

    @Test
    void cancelRegisterWhenPhoneNumberIsTaken() {
        final var registerRequest = new RegisterRequest(
                EMAIL,
                FULL_NAME,
                PASSWORD,
                PASSWORD,
                PHONE_NUMBER
        );

        when(profileRepository.existsByPhoneNumber(PHONE_NUMBER)).thenReturn(true);

        assertThrows(
                ProfilePhoneNumberViolationException.class,
                () -> authService.register(registerRequest)
        );
    }

    @Test
    void completeRegistrationIfEverythingIsCorrect() {
        final var registerRequest = new RegisterRequest(
                EMAIL,
                FULL_NAME,
                PASSWORD,
                PASSWORD,
                PHONE_NUMBER
        );

        when(profileRepository.existsByEmail(EMAIL)).thenReturn(false);
        when(profileRepository.existsByPhoneNumber(PHONE_NUMBER)).thenReturn(false);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);

        assertDoesNotThrow(() -> authService.register(registerRequest));
    }
}
