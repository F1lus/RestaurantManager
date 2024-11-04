package org.restaurantmanager.backend.util.profile;

import org.restaurantmanager.backend.datamodel.entity.ProfileEntity;
import org.restaurantmanager.backend.dto.auth.ProfileRequest;
import org.restaurantmanager.backend.dto.profile.GeneralProfile;
import org.restaurantmanager.backend.dto.profile.UpdateProfileTypeRequest;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.UUID;

public interface IProfileService {
    ProfileEntity getCurrentUser();

    GeneralProfile getCurrentProfile();

    @PreAuthorize("hasAuthority('ADMIN')")
    List<GeneralProfile> getAllProfiles();

    @PreAuthorize("hasAuthority('ADMIN')")
    void updateProfileType(final UUID id, final UpdateProfileTypeRequest request);

    String updateProfile(final UUID id, final ProfileRequest request);
}
