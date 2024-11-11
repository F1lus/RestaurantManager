package org.restaurantmanager.backend.util.profile;

import jakarta.validation.Valid;
import org.restaurantmanager.backend.dto.auth.TokenResponse;
import org.restaurantmanager.backend.dto.profile.GeneralProfile;
import org.restaurantmanager.backend.dto.profile.UpdateProfileRequest;
import org.restaurantmanager.backend.dto.profile.UpdateProfileTypeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static org.restaurantmanager.backend.util.profile.ProfileApi.PROFILE_PATH;

@RequestMapping(PROFILE_PATH)
public interface ProfileApi {

    String PROFILE_PATH = "/api/profile";
    String ALL_PROFILES_PATH = "/all";
    String PROFILE = "/{id}";
    String PROFILE_EDIT = "/edit/{id}";

    @GetMapping
    ResponseEntity<GeneralProfile> getCurrentProfile();

    @GetMapping(ALL_PROFILES_PATH)
    ResponseEntity<Iterable<GeneralProfile>> getAllProfiles();

    @PutMapping(PROFILE)
    ResponseEntity<Void> updateProfileType(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateProfileTypeRequest request
    );

    @PutMapping(PROFILE_EDIT)
    ResponseEntity<TokenResponse> updateProfile(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateProfileRequest request
    );
}
