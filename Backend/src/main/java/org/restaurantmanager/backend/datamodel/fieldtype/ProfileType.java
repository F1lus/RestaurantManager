package org.restaurantmanager.backend.datamodel.fieldtype;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum ProfileType {
    USER,
    WAITER(USER),
    ADMIN(List.of(USER, WAITER));

    private final List<ProfileType> includedProfileTypes = new ArrayList<>();

    ProfileType() {
        this.includedProfileTypes.add(this);
    }

    ProfileType(final ProfileType profileType) {
        this();
        this.includedProfileTypes.add(profileType);
    }

    ProfileType(final List<ProfileType> includedProfileTypes) {
        this();
        this.includedProfileTypes.addAll(includedProfileTypes);
    }

    public List<SimpleGrantedAuthority> toAuthorities() {
        return includedProfileTypes.stream()
                .map(profileType -> new SimpleGrantedAuthority(profileType.name()))
                .toList();
    }
}
