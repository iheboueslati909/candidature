package com.example.candidature.controller;

import com.example.candidature.dto.CandidatureRequest;
import com.example.candidature.dto.CandidatureResponse;
import com.example.candidature.entity.Candidature;
import com.example.candidature.service.CandidatureService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidatures")
public class CandidatureController {

    private final CandidatureService candidatureService;

    public CandidatureController(CandidatureService candidatureService) {
        this.candidatureService = candidatureService;
    }

    @PostMapping
    public ResponseEntity<CandidatureResponse> create(@RequestBody CandidatureRequest request) {
        return ResponseEntity.ok(candidatureService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidatureResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(candidatureService.getById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CandidatureResponse>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(candidatureService.getByUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<CandidatureResponse>> getAll() {
        return ResponseEntity.ok(candidatureService.getAll());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CandidatureResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(candidatureService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        candidatureService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sorted/moyenne")
    public ResponseEntity<List<Candidature>> getSortedByMoyenne() {
        return ResponseEntity.ok(candidatureService.getCandidaturesSortedByMoyenne());
    }
}
