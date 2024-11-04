package org.restaurantmanager.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.restaurantmanager.backend.config.authentication.JwtService;
import org.restaurantmanager.backend.datamodel.entity.ProfileEntity;
import org.restaurantmanager.backend.datamodel.fieldtype.ProfileType;
import org.restaurantmanager.backend.datamodel.repository.ProfileRepository;
import org.restaurantmanager.backend.dto.auth.ProfileRequest;
import org.restaurantmanager.backend.dto.profile.GeneralProfile;
import org.restaurantmanager.backend.dto.profile.UpdateProfileTypeRequest;
import org.restaurantmanager.backend.exception.auth.PasswordConfirmException;
import org.restaurantmanager.backend.exception.profile.ProfileEditDeniedException;
import org.restaurantmanager.backend.exception.profile.ProfileEmailViolationException;
import org.restaurantmanager.backend.exception.profile.ProfileNotFoundException;
import org.restaurantmanager.backend.exception.profile.ProfilePhoneNumberViolationException;
import org.restaurantmanager.backend.util.profile.IProfileService;
import org.restaurantmanager.backend.util.profile.ProfileConverter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

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
    public void updateProfileType(final UUID id, final UpdateProfileTypeRequest request) {
        val searchedProfile = getProfileEntity(id);
        searchedProfile.setProfileType(ProfileType.valueOf(request.getProfileType()));
        profileRepository.save(searchedProfile);
    }

    @Override
    public String updateProfile(final UUID id, final ProfileRequest request) {
        val currentUser = getCurrentUser();
        if (!currentUser.getId().equals(id)) {
            throw new ProfileEditDeniedException();
        }

        if (!request.getPassword().equals(request.getPasswordRepeat())) {
            throw new PasswordConfirmException();
        }

        checkEmail(id, request.getEmail());
        checkPhoneNumber(id, request.getPhoneNumber());

        currentUser.setFullName(request.getFullName());
        currentUser.setEmail(request.getEmail());
        currentUser.setPhoneNumber(request.getPhoneNumber());
        currentUser.setPassword(passwordEncoder.encode(request.getPassword()));

        val updatedProfile = profileRepository.save(currentUser);

        return jwtService.generateToken(updatedProfile);
    }

    private ProfileEntity getProfileEntity(final UUID id) {
        return profileRepository.findById(id)
                .orElseThrow(ProfileNotFoundException::new);
    }

    private void checkEmail(final UUID id, final String email) {
        boolean isEmailTaken = profileRepository.isEmailTaken(id, email);
        if (isEmailTaken) {
            throw new ProfileEmailViolationException();
        }
    }

    private void checkPhoneNumber(final UUID id, final String phoneNumber) {
        val isPhoneNumberTaken = profileRepository.isPhoneNumberTaken(id, phoneNumber);
        if (isPhoneNumberTaken) {
            throw new ProfilePhoneNumberViolationException();
        }
    }
}
