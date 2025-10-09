package com.example.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "builders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Builder {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    
    @Column(name = "participant_id")
    private String participantId;
    
    private String name;
    
    private String email;
    
    @Column(name = "company_name")
    private String companyName;
    
    private String skill;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "job_title")
    private String jobTitle;
    
    @Column(name = "referral_source")
    private String referralSource;
}