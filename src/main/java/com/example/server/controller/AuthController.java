package com.example.server.controller;

import com.example.server.dto.AuthResponse;
import com.example.server.dto.LoginRequest;
import com.example.server.dto.RegisterRequest;
import com.example.server.dto.UserDetailsDto;
import com.example.server.entity.User;
import com.example.server.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Auth endpoint is working");
    }

    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody RegisterRequest request, HttpServletResponse response) {
        authService.register(request);
        return ResponseEntity.ok("User created successfully");
    }

    
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody LoginRequest request, HttpServletResponse response) {
        try {
            //Authenticate user and generate JWT
            AuthResponse authResponse = authService.login(request);

            //Separate token from AuthResponse
            String token = authResponse.getToken();

            //setting age of the cookie based on rememberMe
            long maxAge = request.isRememberMe()
                    ? 7 * 24 * 60 * 60   // 7 days if rememberMe = true
                    : 24 * 60 * 60;      // 1 day if false

            // Set JWT as HttpOnly cookie
            ResponseCookie cookie = ResponseCookie.from("jwt", token)
                    .httpOnly(true)
                    .path("/")
                    .maxAge(maxAge)
                    .build();

            response.addHeader("Set-Cookie", cookie.toString());

            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@CookieValue(name = "jwt", required = false) String token) {

        AuthResponse authResponse = authService.getUserFromToken(token);

        UserDetailsDto user = authResponse.getUser();

        return ResponseEntity.ok(Map.of(
                "fullName", user.getFullName(),
                "username", user.getUsername(),
                "email", user.getEmail()
        ));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signout(HttpServletResponse response) {
        // Overwrite the cookie with maxAge=0 to remove it
        ResponseCookie cookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .path("/")
                .maxAge(0) // expire immediately
                .sameSite("Lax")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok("Signed out successfully");
    }
}


