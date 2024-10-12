package org.restaurantmanager.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.restaurantmanager.backend.datamodel.entity.ProfileEntity;
import org.restaurantmanager.backend.dto.profile.GeneralProfile;
import org.restaurantmanager.backend.util.profile.IProfileService;
import org.restaurantmanager.backend.util.profile.ProfileConverter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileService implements IProfileService {

    @Override
    public ProfileEntity getCurrentUser() {
        return (ProfileEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @Override
    public GeneralProfile getCurrentProfile() {
        val profileEntity = getCurrentUser();

        return ProfileConverter.toResponse(profileEntity);
    }
}
