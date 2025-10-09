package com.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDetailsDto {
    private UUID id;
    private String fullName;
    private String email;
    private String username;
}