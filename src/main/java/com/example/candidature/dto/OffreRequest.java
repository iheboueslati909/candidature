package com.example.candidature.dto;

import java.time.LocalDate;

public record OffreRequest(
        String titre,
        String description,
        LocalDate dateDebut,
        LocalDate dateFin,
        Long etablissementId
) {}