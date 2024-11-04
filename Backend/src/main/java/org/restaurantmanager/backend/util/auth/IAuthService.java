package org.restaurantmanager.backend.util.auth;

import org.restaurantmanager.backend.dto.auth.LoginRequest;
import org.restaurantmanager.backend.dto.auth.ProfileRequest;

public interface IAuthService {

    String authenticate(final LoginRequest loginRequest);

    void register(final ProfileRequest profileRequest);
}
