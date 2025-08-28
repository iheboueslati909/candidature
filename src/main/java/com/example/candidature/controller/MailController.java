package com.example.candidature.controller;

import com.example.candidature.util.EmailSender;
import com.example.candidature.util.EmailTemplateService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import java.util.List;

@RestController
@RequestMapping("/api/mails")
public class MailController {

    private final EmailSender emailSender;
    private final EmailTemplateService templateService;

    public MailController(EmailSender emailSender, EmailTemplateService templateService) {
        this.emailSender = emailSender;
        this.templateService = templateService;
    }

    @PostMapping("/appel")
    public ResponseEntity<String> sendAppelTemplateMultiple(
            @RequestBody MailRequest request
    ) {
        if (request.getRecipients() == null ||  request.getRecipients().size()<1) {
            return new ResponseEntity<>("Empty recipients list", HttpStatus.BAD_REQUEST);
        }

        String content = templateService.renderOfferTemplate(request.getOfferName());
        emailSender.sendAppelMail(request.getRecipients(), "Nouvel appel à candidature", content);
        return ResponseEntity.ok("Mail sent successfully to " + request.getRecipients().size() + " recipients");
    }

    // Inner DTO
    public static class MailRequest {
        private List<String> recipients;
        private String offerName;

        public List<String> getRecipients() { return recipients; }
        public void setRecipients(List<String> recipients) { this.recipients = recipients; }
        public String getOfferName() { return offerName; }
        public void setOfferName(String offerName) { this.offerName = offerName; }
    }
}
