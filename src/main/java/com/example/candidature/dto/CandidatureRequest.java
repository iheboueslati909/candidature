package com.example.candidature.dto;

import java.time.LocalDate;

public record CandidatureRequest(
        String userId,
        String studentName,
        Double moyenne,
        LocalDate dateDebutMobilite,
        Long offreId
) {}