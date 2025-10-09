package com.example.server.entity;

import com.example.server.enums.HackathonType;
import com.example.server.enums.InstituteType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "organizers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organizer {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    
    @Column(name = "organizer_id")
    private String organizerId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "institute_type")
    private InstituteType instituteType;
    
    @Column(name = "institute_name")
    private String instituteName;
    
    private String email;
    
    private String phone;
    
    @Column(name = "job_title")
    private String jobTitle;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "hackathon_type")
    private HackathonType hackathonType;
}
