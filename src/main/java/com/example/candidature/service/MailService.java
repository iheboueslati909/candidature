package com.example.candidature.service;

import com.example.candidature.client.UserClient;
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
    private final UserClient userClient;

    public MailService(OffreRepository offreRepository,
                       EmailTemplateService templateService,
                       EmailSender emailSender,
                       UserClient userClient) {
        this.offreRepository = offreRepository;
        this.templateService = templateService;
        this.emailSender = emailSender;
        this.userClient = userClient;
    }

    public void sendAppelByOfferId(Long offerId, String authHeader) {
        Offre offre = offreRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offre not found with id " + offerId));

        String offerName = offre.getTitre();
        String content = templateService.renderOfferTemplate(offerName);

        List<String> emails = userClient.getAllUserEmails(authHeader);

        emailSender.sendAppelMail(emails, "Nouvel appel à candidature", content);
    }
}