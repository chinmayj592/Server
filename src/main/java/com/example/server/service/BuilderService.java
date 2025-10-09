package com.example.server.service;

import com.example.server.dto.BuilderRequest;
import com.example.server.entity.Builder;
import com.example.server.repository.BuilderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuilderService {
    
    private final BuilderRepository builderRepository;
    
    public Builder createBuilder(BuilderRequest request) {
        Builder builder = new Builder();
        builder.setParticipantId(request.getParticipantId());
        builder.setName(request.getName());
        builder.setEmail(request.getEmail());
        builder.setCompanyName(request.getCompanyName());
        builder.setSkill(request.getSkill());
        builder.setPhoneNumber(request.getPhoneNumber());
        builder.setJobTitle(request.getJobTitle());
        builder.setReferralSource(request.getReferralSource());
        
        return builderRepository.save(builder);
    }
}