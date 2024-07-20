package org.restaurantmanager.backend.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.restaurantmanager.backend.config.authentication.JwtService;
import org.restaurantmanager.backend.datamodel.entity.ProfileEntity;
import org.restaurantmanager.backend.datamodel.repository.ProfileRepository;
import org.restaurantmanager.backend.dto.auth.LoginRequest;
import org.restaurantmanager.backend.dto.auth.RegisterRequest;
import org.restaurantmanager.backend.exception.auth.IncorrectCredentialsException;
import org.restaurantmanager.backend.exception.auth.PasswordConfirmException;
import org.restaurantmanager.backend.util.auth.IAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
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
                loginRequest.getEmail()
        ));

        val profileEntity = profileRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(IncorrectCredentialsException::new);

        log.info("Profile found, generating token for: {}", loginRequest.getEmail());
        return jwtService.generateToken(profileEntity);
    }

    @Override
    public void register(final RegisterRequest registerRequest) {
        log.info("Registration started for user: {}", registerRequest.getEmail());
        if(!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())){
            throw new PasswordConfirmException();
        }

        val profileEntity = ProfileEntity.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .fullName(registerRequest.getFullName())
                .phoneNumber(registerRequest.getPhoneNumber())
                .build();

        log.info("Registration finished for user: {}", registerRequest.getEmail());
        profileRepository.save(profileEntity);
    }
}
