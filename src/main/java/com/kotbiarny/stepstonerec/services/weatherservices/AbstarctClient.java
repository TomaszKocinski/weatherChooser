package com.kotbiarny.stepstonerec.services.weatherservices;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

public abstract class AbstarctClient {

    private final WebClient webClient;

    public AbstarctClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getWeather() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(getPath())
                        .queryParams(getParametersMap())
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    abstract protected LinkedMultiValueMap<String, String> getParametersMap();

    abstract protected String getApiKey();

    abstract protected String getPath();

}
