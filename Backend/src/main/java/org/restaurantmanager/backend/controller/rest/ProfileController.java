package org.restaurantmanager.backend.controller.rest;

import lombok.RequiredArgsConstructor;
import org.restaurantmanager.backend.dto.profile.GeneralProfile;
import org.restaurantmanager.backend.service.ProfileService;
import org.restaurantmanager.backend.util.profile.ProfileApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController implements ProfileApi {

    private final ProfileService profileService;

    @Override
    public ResponseEntity<GeneralProfile> getCurrentProfile() {
        return ResponseEntity.ok(profileService.getCurrentProfile());
    }
}
