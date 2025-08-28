package com.example.candidature.controller;

import com.example.candidature.dto.RatingRequest;
import com.example.candidature.dto.RatingResponse;
import com.example.candidature.service.RatingService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<RatingResponse> create(@RequestBody RatingRequest request) {
        return ResponseEntity.ok(ratingService.create(request));
    }

    @GetMapping("/etablissement/{etabId}")
    public ResponseEntity<List<RatingResponse>> getByEtablissement(@PathVariable Long etabId) {
        return ResponseEntity.ok(ratingService.getByEtablissement(etabId));
    }
}
