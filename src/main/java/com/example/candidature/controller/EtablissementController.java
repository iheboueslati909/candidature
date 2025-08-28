package com.example.candidature.controller;

import com.example.candidature.dto.EtablissementRequest;
import com.example.candidature.dto.EtablissementResponse;
import com.example.candidature.service.EtablissementService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etablissements")
public class EtablissementController {

    private final EtablissementService etablissementService;

    public EtablissementController(EtablissementService etablissementService) {
        this.etablissementService = etablissementService;
    }

    @PostMapping
    public ResponseEntity<EtablissementResponse> create(@RequestBody EtablissementRequest request) {
        return ResponseEntity.ok(etablissementService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtablissementResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(etablissementService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<EtablissementResponse>> getAll() {
        return ResponseEntity.ok(etablissementService.getAll());
    }
}
