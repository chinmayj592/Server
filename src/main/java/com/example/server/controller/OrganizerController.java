package com.example.server.controller;

import com.example.server.dto.OrganizerRequest;
import com.example.server.entity.Organizer;
import com.example.server.service.OrganizerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizers")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class OrganizerController {
    
    private final OrganizerService organizerService;
    
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Organizer endpoint is working");
    }
    
    @PostMapping("/")
    public ResponseEntity<?> createOrganizer(@RequestBody OrganizerRequest request) {
        organizerService.createOrganizer(request);
        return ResponseEntity.ok("Organizer registered successfully");
    }
}