package com.example.server.controller;

import com.example.server.dto.BuilderRequest;
import com.example.server.entity.Builder;
import com.example.server.service.BuilderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/builder")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class BuilderController {
    
    private final BuilderService builderService;
    
    @PostMapping
    public ResponseEntity<?> createBuilder(@Valid @RequestBody BuilderRequest request) {
        builderService.createBuilder(request);
        return ResponseEntity.ok("Builder registered successfully");
    }
}