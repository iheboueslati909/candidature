package com.example.candidature.controller;

import com.example.candidature.entity.Favori;
import com.example.candidature.service.FavoriService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoris")
public class FavoriController {

    private final FavoriService favoriService;

    public FavoriController(FavoriService favoriService) {
        this.favoriService = favoriService;
    }

    @PostMapping
    public ResponseEntity<Favori> addFavori(@RequestParam Long userId, @RequestParam Long offreId) {
        return ResponseEntity.ok(favoriService.addFavori(userId, offreId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Favori>> getUserFavoris(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriService.getFavorisByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFavori(@PathVariable Long id) {
        favoriService.removeFavori(id);
        return ResponseEntity.noContent().build();
    }
}
