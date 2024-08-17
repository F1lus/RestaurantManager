package org.restaurantmanager.backend.util.profile;

import org.restaurantmanager.backend.datamodel.entity.ProfileEntity;

public interface IProfileService {
    ProfileEntity getLoggedInUser();
}
