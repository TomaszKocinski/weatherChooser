package com.kotbiarny.stepstonerec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SpringConfig {


    @Bean(name = "weatherbit")
    public WebClient weatherbit() {
        return preBuildWebClient("https://api.weatherbit.io/");
    }

    @Bean(name = "accuweather")
    public WebClient accuweather() {
        return preBuildWebClient("http://dataservice.accuweather.com/");
    }

    @Bean(name = "OpenWeatherMap")
    public WebClient openWeatherMap() {
        return preBuildWebClient("http://api.openweathermap.org/");

    }

    private WebClient preBuildWebClient(String s) {
        return WebClient.builder()
                .baseUrl(s)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


}
