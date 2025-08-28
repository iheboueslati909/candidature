package com.example.candidature.repository;

import com.example.candidature.entity.Favori;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriRepository extends JpaRepository<Favori, Long> {
    List<Favori> findByUserId(Long userId);
}