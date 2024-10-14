package org.restaurantmanager.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.restaurantmanager.backend.config.authentication.JwtService;
import org.restaurantmanager.backend.datamodel.entity.ProfileEntity;
import org.restaurantmanager.backend.datamodel.fieldtype.ProfileType;
import org.restaurantmanager.backend.datamodel.repository.ProfileRepository;
import org.restaurantmanager.backend.dto.auth.LoginRequest;
import org.restaurantmanager.backend.dto.auth.RegisterRequest;
import org.restaurantmanager.backend.exception.auth.IncorrectCredentialsException;
import org.restaurantmanager.backend.exception.auth.PasswordConfirmException;
import org.restaurantmanager.backend.exception.profile.ProfileEmailViolationException;
import org.restaurantmanager.backend.exception.profile.ProfilePhoneNumberViolationException;
import org.restaurantmanager.backend.util.auth.IAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AuthService implements IAuthService {

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String authenticate(final LoginRequest loginRequest) {
        log.info("Authenticating user: {}", loginRequest.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        val profileEntity = profileRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(IncorrectCredentialsException::new);

        log.info("Profile found, generating token for: {}", loginRequest.getEmail());
        return jwtService.generateToken(profileEntity);
    }

    @Override
    public void register(final RegisterRequest registerRequest) {
        log.info("Registration started for user: {}", registerRequest.getEmail());
        if (!registerRequest.getPassword().equals(registerRequest.getPasswordRepeat())) {
            throw new PasswordConfirmException();
        }

        checkEmail(registerRequest.getEmail());
        checkPhoneNumber(registerRequest.getPhoneNumber());

        val profileEntity = ProfileEntity.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .fullName(registerRequest.getFullName())
                .phoneNumber(registerRequest.getPhoneNumber())
                .profileType(ProfileType.USER)
                .build();

        profileRepository.save(profileEntity);
        log.info("Registration finished for user: {}", registerRequest.getEmail());
    }

    private void checkEmail(final String email) {
        boolean isEmailTaken = profileRepository.existsByEmail(email);
        if (isEmailTaken) {
            throw new ProfileEmailViolationException();
        }
    }

    private void checkPhoneNumber(final String phoneNumber) {
        val isPhoneNumberTaken = profileRepository.existsByPhoneNumber(phoneNumber);
        if (isPhoneNumberTaken) {
            throw new ProfilePhoneNumberViolationException();
        }
    }
}
