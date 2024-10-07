package org.restaurantmanager.backend.util.profile;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.restaurantmanager.backend.datamodel.entity.ProfileEntity;
import org.restaurantmanager.backend.dto.profile.GeneralProfile;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProfileConverter {

    public static GeneralProfile toResponse(ProfileEntity profileEntity) {
        return GeneralProfile.builder()
                .id(profileEntity.getId())
                .email(profileEntity.getEmail())
                .fullName(profileEntity.getFullName())
                .phoneNumber(profileEntity.getPhoneNumber())
                .profileType(profileEntity.getProfileType())
                .build();
    }
}
