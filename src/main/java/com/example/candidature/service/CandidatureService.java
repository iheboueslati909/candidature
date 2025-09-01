package com.example.candidature.service;

import com.example.candidature.dto.CandidatureRequest;
import com.example.candidature.dto.CandidatureResponse;
import com.example.candidature.entity.Candidature;
import com.example.candidature.entity.Offre;
import com.example.candidature.enums.CandidatureStatus;
import com.example.candidature.repository.CandidatureRepository;
import com.example.candidature.repository.OffreRepository;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatureService {

    private final CandidatureRepository candidatureRepository;
    private final OffreRepository offreRepository;

    public CandidatureService(CandidatureRepository candidatureRepository, OffreRepository offreRepository) {
        this.candidatureRepository = candidatureRepository;
        this.offreRepository = offreRepository;
    }

    public CandidatureResponse create(CandidatureRequest request) {
        Offre offre = offreRepository.findById(request.offreId())
                .orElseThrow(() -> new ResourceNotFoundException("Offre not found with id " + request.offreId()));

        Candidature candidature = new Candidature();
    candidature.setUserId(request.userId());
        candidature.setStudentName(request.studentName());
        candidature.setMoyenne(request.moyenne());
        candidature.setDateDebutMobilite(request.dateDebutMobilite());
        candidature.setStatus(CandidatureStatus.PENDING);
        candidature.setOffre(offre);

        return toResponse(candidatureRepository.save(candidature));
    }

    public CandidatureResponse getById(Long id) {
        return candidatureRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Candidature not found with id " + id));
    }

    public List<CandidatureResponse> getByUser(String userId) {
        return candidatureRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<CandidatureResponse> getAll() {
        return candidatureRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CandidatureResponse updateStatus(Long id, String status) {
        Candidature candidature = candidatureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidature not found with id " + id));

        candidature.setStatus(CandidatureStatus.valueOf(status.toUpperCase()));
        return toResponse(candidatureRepository.save(candidature));
    }

    public void delete(Long id) {
        if (!candidatureRepository.existsById(id)) {
            throw new ResourceNotFoundException("Candidature not found with id " + id);
        }
        candidatureRepository.deleteById(id);
    }

    private CandidatureResponse toResponse(Candidature c) {
        Offre offre = c.getOffre();
        String offreTitre = (offre != null) ? offre.getTitre() : null;
        String etabNom = (offre != null && offre.getEtablissement() != null) ? offre.getEtablissement().getNom() : null;

        return new CandidatureResponse(
                c.getId(),
                c.getUserId(),
                c.getStudentName(),
                c.getMoyenne(),
                c.getDateDebutMobilite(),
                c.getStatus().name(),
                (offre != null) ? offre.getId() : null,
                offreTitre,
                etabNom,
                c.getCreatedAt()
        );
    }

    public List<Candidature> getCandidaturesSortedByMoyenne() {
        return candidatureRepository.findAllByOrderByMoyenneDesc();
    }
}
