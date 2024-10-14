package org.restaurantmanager.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.restaurantmanager.backend.datamodel.entity.ProfileEntity;
import org.restaurantmanager.backend.datamodel.fieldtype.ProfileType;
import org.restaurantmanager.backend.datamodel.repository.ProfileRepository;
import org.restaurantmanager.backend.dto.profile.GeneralProfile;
import org.restaurantmanager.backend.dto.profile.UpdateProfileTypeRequest;
import org.restaurantmanager.backend.exception.profile.ProfileNotFoundException;
import org.restaurantmanager.backend.util.profile.IProfileService;
import org.restaurantmanager.backend.util.profile.ProfileConverter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProfileService implements IProfileService {

    private final ProfileRepository profileRepository;

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

    @Override
    public List<GeneralProfile> getAllProfiles() {
        val currentProfileEntity = getCurrentUser();
        return profileRepository.findAllByIdNotIn(List.of(currentProfileEntity.getId()))
                .stream()
                .map(ProfileConverter::toResponse)
                .toList();
    }

    @Override
    public void updateProfileType(UUID id, UpdateProfileTypeRequest request) {
        val searchedProfile = getProfileEntity(id);
        searchedProfile.setProfileType(ProfileType.valueOf(request.getProfileType()));
        profileRepository.save(searchedProfile);
    }

    private ProfileEntity getProfileEntity(UUID id) {
        return profileRepository.findById(id)
                .orElseThrow(ProfileNotFoundException::new);
    }
}
