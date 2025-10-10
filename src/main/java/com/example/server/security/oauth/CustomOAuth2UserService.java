package com.example.server.security.oauth;

import com.example.server.entity.User;
import com.example.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        Map<String, Object> attributes = oAuth2User.getAttributes();

        // Extract basic fields from Google or generic OpenID Connect providers
        String email = (String) attributes.getOrDefault("email", "");
        String name = (String) attributes.getOrDefault("name", "");
        if (email == null || email.isBlank()) {
            // Try nested structure (some providers)
            Object emailObj = attributes.get("emails");
            if (emailObj instanceof String s) {
                email = s;
            }
        }

        if (name == null || name.isBlank()) {
            String givenName = (String) attributes.getOrDefault("given_name", "");
            String familyName = (String) attributes.getOrDefault("family_name", "");
            name = (givenName + " " + familyName).trim();
        }

        if (email == null || email.isBlank()) {
            throw new OAuth2AuthenticationException("Email not provided by OAuth2 provider: " + registrationId);
        }

        // Ensure a local user exists
        Optional<User> existing = userRepository.findByEmail(email);
        User user;
        if (existing.isPresent()) {
            user = existing.get();
        } else {
            User u = new User();
            u.setFullName(name == null || name.isBlank() ? email : name);
            u.setEmail(email);
            String baseUsername = email.contains("@") ? email.substring(0, email.indexOf('@')) : ("user_" + UUID.randomUUID().toString().substring(0, 8));
            String finalUsername = ensureUniqueUsername(baseUsername);
            u.setUsername(finalUsername);
            u.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
            user = userRepository.save(u);
        }

        // We return the original OAuth2User; authorities come from it
        return new DefaultOAuth2User(oAuth2User.getAuthorities(), attributes, "sub");
    }

    private String ensureUniqueUsername(String base) {
        String candidate = base;
        int i = 0;
        while (userRepository.existsByUsername(candidate)) {
            i++;
            candidate = base + i;
        }
        return candidate;
    }
}
