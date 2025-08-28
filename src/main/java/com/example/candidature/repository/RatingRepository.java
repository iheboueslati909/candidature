package com.example.candidature.repository;

import com.example.candidature.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByEtablissementId(Long etabId);

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.etablissement.id = :etabId")
    double calculateAverageByEtablissement(Long etabId);
}