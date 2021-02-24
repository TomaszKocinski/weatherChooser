package com.kotbiarny.stepstonerec.services.weatherservices;

import com.kotbiarny.stepstonerec.enums.WeatherProviders;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@Getter
public class OpenWeatherMap extends AbstarctClient implements WeatherClients {

    @Value("${apiKey.OpenWeatherMap}")
    public String apiKey;

    public OpenWeatherMap(@Qualifier("OpenWeatherMap") WebClient webClient) {
        super(webClient);
    }

    @Override
    public WeatherProviders getWeatherProviderName() {
        return WeatherProviders.OPENWEATHERMAP;
    }

    @Override
    public LinkedMultiValueMap<String, String> getParametersMap() {
        Map<String, List<String>> stringStringMap = Map.of("q", Collections.singletonList("Warsaw")
                , "appid", Collections.singletonList(getApiKey()));
        return new LinkedMultiValueMap<>(stringStringMap);
    }

    @Override
    public String getPath() {
        return "data/2.5/weather";
    }
}
