package com.example.server.service;

import com.example.server.dto.OrganizerRequest;
import com.example.server.entity.Organizer;
import com.example.server.repository.OrganizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizerService {
    
    private final OrganizerRepository organizerRepository;
    
    public Organizer createOrganizer(OrganizerRequest request) {
        Organizer organizer = new Organizer();
        organizer.setOrganizerId(request.getOrganizerId());
        organizer.setInstituteType(request.getInstituteType());
        organizer.setInstituteName(request.getInstituteName());
        organizer.setEmail(request.getEmail());
        organizer.setPhone(request.getPhone());
        organizer.setJobTitle(request.getJobTitle());
        organizer.setHackathonType(request.getHackathonType());
        
        return organizerRepository.save(organizer);
    }
}


