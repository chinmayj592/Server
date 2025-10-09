package com.example.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class TestController {
    
    @GetMapping("/test")
    public ResponseEntity<?> testConnection() {
        return ResponseEntity.ok("Backend is connected!");
    }
    
    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok("pong");
    }
    
    @RequestMapping(value = "/test-signup", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public ResponseEntity<?> testSignup(
            @RequestBody(required = false) Object request,
            HttpServletRequest httpRequest) {
        String method = httpRequest.getMethod();
        System.out.println("Test signup " + method + " request received");
        return ResponseEntity.ok("Test signup successful! Method: " + method);
    }
    
    @RequestMapping(value = "/signup", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public ResponseEntity<?> signup(@RequestBody(required = false) Object request) {
        System.out.println("Signup endpoint hit");
        return ResponseEntity.ok("Signup test successful!");
    }
    
    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public ResponseEntity<?> register(@RequestBody(required = false) Object request) {
        System.out.println("Register endpoint hit");
        return ResponseEntity.ok("Register test successful!");
    }
}