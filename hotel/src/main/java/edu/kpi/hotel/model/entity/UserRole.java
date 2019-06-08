package edu.kpi.hotel.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    CLIENT,
    ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return toString();
    }
}
