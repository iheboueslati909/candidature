package com.example.candidature.client;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UserClient {

    private final WebClient webClient;

    public UserClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8885/mobility/users/all").build();
    }

    public List<String> getAllUserEmails(String authHeader) {
        System.out.println("Auth Header: " + authHeader);
        return webClient.get()
                .header("Authorization", authHeader)
                .retrieve()
                .bodyToMono(
                        new org.springframework.core.ParameterizedTypeReference<java.util.Map<String, Object>>() {
                        })
                .map(map -> (List<?>) map.get("content"))
                .map(content -> content.stream()
                        .map(o -> ((java.util.Map<?, ?>) o).get("email").toString())
                        .toList())
                .block();
    }
}