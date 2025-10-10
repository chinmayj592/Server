package com.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private UserDetailsDto user;

    public AuthResponse(UserDetailsDto user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDetailsDto getUser() {
        return user;
    }

    public void setUser(UserDetailsDto user) {
        this.user = user;
    }
}