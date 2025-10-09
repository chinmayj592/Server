package com.example.server.dto;

import com.example.server.enums.HackathonType;
import com.example.server.enums.InstituteType;
import lombok.Data;

@Data
public class OrganizerRequest {
    private String organizerId;
    private InstituteType instituteType;
    private String instituteName;
    private String email;
    private String phone;
    private String jobTitle;
    private HackathonType hackathonType;
}