package com.alic3.versioned.catalog;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class WebClientHelper {


    public <T> T fetchWithRetry(String uri, Class<T> responseType) {
        int maxRetries = 3;
        int retryDelayMillis = 0;

        WebClient webClient = WebClient.builder()
                .baseUrl("https://tienda.mercadona.es")
                .build();

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                T response = webClient.get()
                        .uri(uri)
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                        .retrieve()
                        .onStatus(status -> status.isError(), res -> {
                            System.err.println("HTTP Error: " + res.statusCode());
                            return Mono.error(new RuntimeException("Server returned error status"));
                        })
                        .bodyToMono(responseType)
                        .block();

                // Espera tras éxito (para no abusar)
                Thread.sleep(retryDelayMillis);

                return response;

            } catch (Exception e) {
                System.err.println("Attempt " + attempt + " failed for " + uri + ": " + e.getMessage());

                if (attempt == maxRetries) {
                    System.err.println("Max retries reached. Skipping " + uri);
                    return null;
                }

                try {
                    Thread.sleep(retryDelayMillis); // Espera antes de reintentar
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        return null;
    }
}
