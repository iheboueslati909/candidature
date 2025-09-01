package com.example.candidature.controller;

import com.example.candidature.dto.ProfileEtudiantRequest;
import com.example.candidature.dto.ProfileEtudiantResponse;
import com.example.candidature.service.ProfileEtudiantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileEtudiantController {

    private final ProfileEtudiantService service;

    public ProfileEtudiantController(ProfileEtudiantService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProfileEtudiantResponse> create(@RequestBody ProfileEtudiantRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileEtudiantResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileEtudiantResponse> getByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<ProfileEtudiantResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileEtudiantResponse> update(@PathVariable Long id, @RequestBody ProfileEtudiantRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
