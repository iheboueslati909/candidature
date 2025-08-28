package com.example.candidature.dto;


public record RatingRequest(
        Long userId,
        Long etablissementId,
        int score
) {}