package com.example.candidature.service;

import com.example.candidature.entity.Offre;
import com.example.candidature.repository.OffreRepository;
import com.example.candidature.util.EmailSender;
import com.example.candidature.util.EmailTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {

    private final OffreRepository offreRepository;
    private final EmailTemplateService templateService;
    private final EmailSender emailSender;

    public MailService(OffreRepository offreRepository,
                       EmailTemplateService templateService,
                       EmailSender emailSender) {
        this.offreRepository = offreRepository;
        this.templateService = templateService;
        this.emailSender = emailSender;
    }

    public void sendAppelByOfferId(Long offerId) {
        Offre offre = offreRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offre not found with id " + offerId));

        String offerName = offre.getTitre();
        String content = templateService.renderOfferTemplate(offerName);

        // Mocked static list of users (emails). Replace with real user lookup later.
        // TODO
        List<String> users = List.of(
                "alice@example.com",
                "bob@example.com",
                "charlie@example.com",
                "oueslatiiheb0@gmail.com"
        );

        emailSender.sendAppelMail(users, "Nouvel appel à candidature", content);
    }
}
