package org.restaurantmanager.backend.util.auth;

import org.restaurantmanager.backend.dto.auth.LoginRequest;
import org.restaurantmanager.backend.dto.auth.RegisterRequest;
import org.restaurantmanager.backend.dto.profile.GeneralProfile;

import java.security.Principal;

public interface IAuthService {

    String authenticate(final LoginRequest loginRequest);
    
    void register(final RegisterRequest registerRequest);

    GeneralProfile getCurrentUser(final Principal principal);
}
