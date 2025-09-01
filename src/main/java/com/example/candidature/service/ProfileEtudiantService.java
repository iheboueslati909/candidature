package com.example.candidature.service;

import com.example.candidature.dto.ProfileEtudiantRequest;
import com.example.candidature.dto.ProfileEtudiantResponse;
import com.example.candidature.entity.ProfileEtudiant;
import com.example.candidature.repository.ProfileEtudiantRepository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileEtudiantService {

    private final ProfileEtudiantRepository repository;

    public ProfileEtudiantService(ProfileEtudiantRepository repository) {
        this.repository = repository;
    }

    public ProfileEtudiantResponse create(ProfileEtudiantRequest request) {
        ProfileEtudiant p = new ProfileEtudiant();
        p.setUserId(request.userId());
        p.setMoyenne(request.moyenne());
        return toResponse(repository.save(p));
    }

    public ProfileEtudiantResponse getById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("ProfileEtudiant not found with id " + id));
    } 

    public ProfileEtudiantResponse getByUserId(String userId) {
                ProfileEtudiant profile = repository.findByUserId(userId)
                .orElseGet(() -> {
                    ProfileEtudiant newProfile = new ProfileEtudiant();
                    newProfile.setUserId(userId);
                    newProfile.setMoyenne(0.0);
                    return repository.save(newProfile);
                });
        return toResponse(profile);
    }

    public List<ProfileEtudiantResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProfileEtudiantResponse update(Long id, ProfileEtudiantRequest request) {
        ProfileEtudiant existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProfileEtudiant not found with id " + id));
        existing.setUserId(request.userId());
        existing.setMoyenne(request.moyenne());
        return toResponse(repository.save(existing));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("ProfileEtudiant not found with id " + id);
        }
        repository.deleteById(id);
    }

    private ProfileEtudiantResponse toResponse(ProfileEtudiant p) {
        return new ProfileEtudiantResponse(p.getId(), p.getUserId(), p.getMoyenne());
    }
}
