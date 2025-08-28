package com.example.candidature.dto;

import java.time.LocalDateTime;

public record RatingResponse(
        Long id,
        Long userId,
        Long etablissementId,
        int score,
        LocalDateTime createdAt
) {}