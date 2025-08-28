package com.example.candidature.service;

import com.example.candidature.dto.RatingRequest;
import com.example.candidature.dto.RatingResponse;
import com.example.candidature.entity.Etablissement;
import com.example.candidature.entity.Rating;
import com.example.candidature.repository.EtablissementRepository;
import com.example.candidature.repository.RatingRepository;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final EtablissementRepository etablissementRepository;

    public RatingService(RatingRepository ratingRepository, EtablissementRepository etablissementRepository) {
        this.ratingRepository = ratingRepository;
        this.etablissementRepository = etablissementRepository;
    }

    public RatingResponse create(RatingRequest request) {
        Etablissement etablissement = etablissementRepository.findById(request.etablissementId())
                .orElseThrow(() -> new ResourceNotFoundException("Etablissement not found with id " + request.etablissementId()));

        Rating rating = new Rating();
        rating.setUserId(request.userId());
        rating.setEtablissement(etablissement);
        rating.setScore(request.score());

        Rating saved = ratingRepository.save(rating);

        // update average rating
        double avg = ratingRepository.calculateAverageByEtablissement(etablissement.getId());
        etablissement.setAverageRating(avg);
        etablissementRepository.save(etablissement);

        return toResponse(saved);
    }

    public List<RatingResponse> getByEtablissement(Long etabId) {
        return ratingRepository.findByEtablissementId(etabId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private RatingResponse toResponse(Rating r) {
        return new RatingResponse(
                r.getId(),
                r.getUserId(),
                r.getEtablissement().getId(),
                r.getScore(),
                r.getCreatedAt()
        );
    }
}
