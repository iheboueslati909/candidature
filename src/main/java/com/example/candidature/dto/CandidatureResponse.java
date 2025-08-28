package com.example.candidature.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CandidatureResponse(
        Long id,
        Long userId,
        String studentName,
        Double moyenne,
        LocalDate dateDebutMobilite,
        String status,
        Long offreId,
        LocalDateTime createdAt
) {}