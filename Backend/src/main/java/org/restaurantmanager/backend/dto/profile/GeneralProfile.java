package org.restaurantmanager.backend.dto.profile;

import lombok.Builder;
import lombok.Getter;
import org.restaurantmanager.backend.datamodel.fieldtype.ProfileType;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public final class GeneralProfile {
    private final UUID id;
    private final String fullName;
    private final String email;
    private final String phoneNumber;
    private final List<ProfileType> profileTypes;
}
