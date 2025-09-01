package com.example.candidature.controller;

import com.example.candidature.service.MailService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mails")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/appel")
    public ResponseEntity<String> sendAppelTemplateByOfferId(
            @RequestBody MailRequest request,
            HttpServletRequest servletRequest) {

        if (request == null || request.getOfferId() == null) {
            return new ResponseEntity<>("Missing offerId", HttpStatus.BAD_REQUEST);
        }

        String authHeader = servletRequest.getHeader("Authorization");
        mailService.sendAppelByOfferId(request.getOfferId(), authHeader);

        return ResponseEntity.ok("Mail sent successfully for offerId " + request.getOfferId());
    }

    // Inner DTO
    public static class MailRequest {
        private Long offerId;

        public Long getOfferId() { return offerId; }
        public void setOfferId(Long offerId) { this.offerId = offerId; }
    }
}