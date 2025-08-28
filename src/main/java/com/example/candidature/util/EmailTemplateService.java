package com.example.candidature.util;

import org.springframework.stereotype.Service;

@Service
public class EmailTemplateService {

    private static final String TEMPLATE = """
        <html>
            <body>
                <h2>Nouvel appel à candidature</h2>
                <p>Bonjour,</p>
                <p>Une nouvelle offre est disponible : <b>%s</b></p>
                <p>Nous vous invitons à postuler avant la date limite.</p>
                <br>
                <p>Cordialement,<br>L'équipe Mobilité Internationale</p>
            </body>
        </html>
        """;

    public String renderOfferTemplate(String offerName) {
        return String.format(TEMPLATE, offerName);
    }
}
