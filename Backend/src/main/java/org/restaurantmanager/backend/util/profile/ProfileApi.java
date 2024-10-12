package org.restaurantmanager.backend.util.profile;

import org.restaurantmanager.backend.dto.profile.GeneralProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.restaurantmanager.backend.util.profile.ProfileApi.PROFILE_PATH;

@RequestMapping(PROFILE_PATH)
public interface ProfileApi {

    String PROFILE_PATH = "/api/profile";

    @GetMapping
    ResponseEntity<GeneralProfile> getCurrentProfile();
}
