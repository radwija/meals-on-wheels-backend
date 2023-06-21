package com.lithan.mow.payload.response;

import java.util.List;
import java.util.Set;

public class AuthResponse {
    private String email;
    private String accessToken;
    private Set<String> Role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Set<String> getRole() {
        return Role;
    }

    public void setRole(Set<String> role) {
        Role = role;
    }
}
