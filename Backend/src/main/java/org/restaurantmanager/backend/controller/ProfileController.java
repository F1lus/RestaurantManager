package org.restaurantmanager.backend.controller;

import lombok.RequiredArgsConstructor;
import org.restaurantmanager.backend.dto.auth.TokenResponse;
import org.restaurantmanager.backend.dto.profile.GeneralProfile;
import org.restaurantmanager.backend.dto.profile.UpdateProfileRequest;
import org.restaurantmanager.backend.dto.profile.UpdateProfileTypeRequest;
import org.restaurantmanager.backend.util.profile.IProfileService;
import org.restaurantmanager.backend.util.profile.ProfileApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProfileController implements ProfileApi {

    private final IProfileService profileService;

    @Override
    public ResponseEntity<GeneralProfile> getCurrentProfile() {
        return ResponseEntity.ok(profileService.getCurrentProfile());
    }

    @Override
    public ResponseEntity<Iterable<GeneralProfile>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @Override
    public ResponseEntity<Void> updateProfileType(UUID id, UpdateProfileTypeRequest request) {
        profileService.updateProfileType(id, request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<TokenResponse> updateProfile(UUID id, UpdateProfileRequest request) {
        return ResponseEntity.ok(
                new TokenResponse(
                        profileService.updateProfile(id, request)
                )
        );
    }
}
