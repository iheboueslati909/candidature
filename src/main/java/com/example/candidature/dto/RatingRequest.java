package com.example.candidature.dto;


public record RatingRequest(
        String userId,
        Long etablissementId,
        int score
) {}