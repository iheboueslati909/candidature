package com.example.candidature.service;

import com.example.candidature.entity.Favori;
import com.example.candidature.entity.Offre;
import com.example.candidature.repository.FavoriRepository;
import com.example.candidature.repository.OffreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriService {

    private final FavoriRepository favoriRepository;
    private final OffreRepository offreRepository;

    public FavoriService(FavoriRepository favoriRepository, OffreRepository offreRepository) {
        this.favoriRepository = favoriRepository;
        this.offreRepository = offreRepository;
    }

    public Favori addFavori(Long userId, Long offreId) {
        Offre offre = offreRepository.findById(offreId)
                .orElseThrow(() -> new RuntimeException("Offre not found"));

        Favori favori = new Favori();
        favori.setUserId(userId);
        favori.setOffre(offre);
        return favoriRepository.save(favori);
    }

    public List<Favori> getFavorisByUser(Long userId) {
        return favoriRepository.findByUserId(userId);
    }

    public void removeFavori(Long id) {
        favoriRepository.deleteById(id);
    }
}
