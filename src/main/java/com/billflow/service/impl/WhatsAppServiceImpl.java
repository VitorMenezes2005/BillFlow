package com.billflow.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WhatsAppServiceImpl {

    private final WebClient webClient;
    private final String instance;

    public WhatsAppServiceImpl(WebClient.Builder builder,
                               @Value("${whatsapp.base-url}") String baseUrl,
                               @Value("${whatsapp.api-key}") String apiKey,
                               @Value("${whatsapp.instance}") String instance) {
        this.webClient = builder
                .baseUrl(baseUrl)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("apikey", apiKey)
                .build();
        this.instance = instance;
    }

    public String sendMessage(String number, String text){
        String json = """
                {
                    "number": "%s",
                    "text": "%s"
                }
                """.formatted(number, text);

        return webClient.post()
                .uri("/message/sendText/{instance}", instance)
                .bodyValue(json)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
