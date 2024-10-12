package org.restaurantmanager.backend.util.profile;

import org.restaurantmanager.backend.datamodel.entity.ProfileEntity;
import org.restaurantmanager.backend.dto.profile.GeneralProfile;

public interface IProfileService {
    ProfileEntity getCurrentUser();

    GeneralProfile getCurrentProfile();
}
