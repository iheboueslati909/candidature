package com.example.candidature.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @GetMapping(value = "/stream/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public SseEmitter streamNotifications(@PathVariable String userId) {
    SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
    emitters.put(userId, emitter);

    emitter.onCompletion(() -> emitters.remove(userId));
    emitter.onTimeout(() -> emitters.remove(userId));

    System.out.println("SSE connected for userId=" + userId);
    return emitter;
}

private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

public void sendNotification(String userId, String message) {
    SseEmitter emitter = emitters.get(userId);
    if (emitter != null) {
        try {
            System.out.println("Sending notification to userId=" + userId + ": " + message);
            emitter.send(SseEmitter.event().name("notification").data(message));
        } catch (IOException e) {
            emitters.remove(userId);
        }
    }
}
}