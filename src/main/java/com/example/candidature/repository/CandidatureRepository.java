package com.example.candidature.repository;

import com.example.candidature.entity.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
    List<Candidature> findByUserId(Long userId);
    List<Candidature> findAllByOrderByMoyenneDesc();
}
