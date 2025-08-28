package com.example.candidature.controller;

import com.example.candidature.dto.OffreRequest;
import com.example.candidature.dto.OffreResponse;
import com.example.candidature.service.OffreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offres")
public class OffreController {

    private final OffreService offreService;

    public OffreController(OffreService offreService) {
        this.offreService = offreService;
    }

    @PostMapping
    public ResponseEntity<OffreResponse> create(@RequestBody OffreRequest request) {
        return ResponseEntity.ok(offreService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OffreResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(offreService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<OffreResponse>> getAll() {
        return ResponseEntity.ok(offreService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        offreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
