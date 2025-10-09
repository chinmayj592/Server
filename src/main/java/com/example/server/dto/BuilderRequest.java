package com.example.server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BuilderRequest {
    private String participantId;
    
    @NotBlank
    private String name;
    
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    private String companyName;
    
    @NotBlank
    private String skill;
    
    @NotBlank
    private String phoneNumber;
    
    @NotBlank
    private String jobTitle;
    
    @NotBlank
    private String referralSource;
}