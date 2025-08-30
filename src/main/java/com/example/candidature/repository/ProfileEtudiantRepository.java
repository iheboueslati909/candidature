package com.example.candidature.repository;

import com.example.candidature.entity.ProfileEtudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileEtudiantRepository extends JpaRepository<ProfileEtudiant, Long> {
    Optional<ProfileEtudiant> findByUserId(Long userId);
}
