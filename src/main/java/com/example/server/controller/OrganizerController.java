package com.example.server.controller;

import com.example.server.dto.OrganizerRequest;
import com.example.server.service.OrganizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizer")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class OrganizerController {
    
    private final OrganizerService organizerService;

    @PostMapping
    public ResponseEntity<?> createOrganizer(@RequestBody OrganizerRequest request) {
        organizerService.createOrganizer(request);
        return ResponseEntity.ok("Organizer registered successfully");
    }
}