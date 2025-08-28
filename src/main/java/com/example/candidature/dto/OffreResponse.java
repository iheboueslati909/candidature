package com.example.candidature.dto;

import java.time.LocalDate;

public record OffreResponse(
        Long id,
        String titre,
        String description,
        LocalDate dateDebut,
        LocalDate dateFin,
        boolean active,
        Long etablissementId
) {}