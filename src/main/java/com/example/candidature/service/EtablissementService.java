package com.example.candidature.service;

import com.example.candidature.dto.EtablissementRequest;
import com.example.candidature.dto.EtablissementResponse;
import com.example.candidature.entity.Etablissement;
import com.example.candidature.repository.EtablissementRepository;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtablissementService {

    private final EtablissementRepository etablissementRepository;

    public EtablissementService(EtablissementRepository etablissementRepository) {
        this.etablissementRepository = etablissementRepository;
    }

    public EtablissementResponse create(EtablissementRequest request) {
        Etablissement etablissement = new Etablissement();
        etablissement.setNom(request.nom());
        etablissement.setPays(request.pays());
        etablissement.setAverageRating(0.0);

        return toResponse(etablissementRepository.save(etablissement));
    }

    public EtablissementResponse getById(Long id) {
        return etablissementRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Etablissement not found with id " + id));
    }

    public List<EtablissementResponse> getAll() {
        return etablissementRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private EtablissementResponse toResponse(Etablissement e) {
        return new EtablissementResponse(
                e.getId(),
                e.getNom(),
                e.getPays(),
                e.getAverageRating()
        );
    }
}
