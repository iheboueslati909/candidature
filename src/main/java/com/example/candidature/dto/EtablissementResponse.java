package com.example.candidature.dto;

public record EtablissementResponse(
        Long id,
        String nom,
        String pays,
        Double averageRating
) {}