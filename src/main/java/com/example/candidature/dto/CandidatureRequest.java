package com.example.candidature.dto;

import java.time.LocalDate;

public record CandidatureRequest(
        Long userId,
        String studentName,
        Double moyenne,
        LocalDate dateDebutMobilite,
        Long offreId
) {}