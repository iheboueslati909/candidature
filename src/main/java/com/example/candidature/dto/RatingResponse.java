package com.example.candidature.dto;

import java.time.LocalDateTime;

public record RatingResponse(
        Long id,
        String userId,
        Long etablissementId,
        int score,
        LocalDateTime createdAt
) {}