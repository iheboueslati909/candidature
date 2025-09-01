package com.example.candidature.service;

import com.example.candidature.controller.NotificationController;
import com.example.candidature.dto.OffreRequest;
import com.example.candidature.dto.OffreResponse;
import com.example.candidature.entity.Etablissement;
import com.example.candidature.entity.Offre;
import com.example.candidature.repository.EtablissementRepository;
import com.example.candidature.repository.OffreRepository;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OffreService {


    private final OffreRepository offreRepository;
    private final EtablissementRepository etablissementRepository;

    public OffreService(OffreRepository offreRepository, EtablissementRepository etablissementRepository
    ) {
        this.offreRepository = offreRepository;
        this.etablissementRepository = etablissementRepository;
    }

    public OffreResponse create(OffreRequest request) {
        Etablissement etablissement = etablissementRepository.findById(request.etablissementId())
                .orElseThrow(() -> new ResourceNotFoundException("Etablissement not found with id " + request.etablissementId()));
        Offre offre = new Offre();
        offre.setTitre(request.titre());
        offre.setDescription(request.description());
        offre.setDateDebut(request.dateDebut());
        offre.setDateFin(request.dateFin());
        offre.setActive(true);
        offre.setEtablissement(etablissement);
        offre.setCreatedBy(request.createdBy());

        return toResponse(offreRepository.save(offre));
    }

    public OffreResponse getById(Long id) {
        return offreRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Offre not found with id " + id));
    }

    public List<OffreResponse> getAll() {
        return offreRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        if (!offreRepository.existsById(id)) {
            throw new ResourceNotFoundException("Offre not found with id " + id);
        }
        offreRepository.deleteById(id);
    }

    private OffreResponse toResponse(Offre o) {
        return new OffreResponse(
                o.getId(),
                o.getTitre(),
                o.getDescription(),
                o.getDateDebut(),
                o.getDateFin(),
                o.isActive(),
                o.getEtablissement().getId()
        );
    }
}
