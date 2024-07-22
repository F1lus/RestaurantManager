package org.restaurantmanager.backend.datamodel.fieldtype;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public enum ProfileType {
    USER,
    WAITER(USER),
    ADMIN(List.of(USER, WAITER));

    public final List<ProfileType> includedProfileTypes = new ArrayList<>();

    ProfileType() {
        this.includedProfileTypes.add(this);
    }

    ProfileType(ProfileType profileType) {
        this();
        this.includedProfileTypes.add(profileType);
    }

    ProfileType(List<ProfileType> includedProfileTypes) {
        this();
        this.includedProfileTypes.addAll(includedProfileTypes);
    }

    public List<SimpleGrantedAuthority> toAuthorities() {
        return includedProfileTypes.stream()
                .map(profileType -> new SimpleGrantedAuthority(profileType.name()))
                .toList();
    }
}
