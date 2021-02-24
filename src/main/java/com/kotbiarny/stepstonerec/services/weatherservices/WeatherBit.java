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
public class WeatherBit extends AbstarctClient implements WeatherClients {

    @Value("${apiKey.weatherbit}")
    public String apiKey;

    public WeatherBit(@Qualifier("weatherbit") WebClient webClient) {
        super(webClient);
    }

    @Override
    public WeatherProviders getWeatherProviderName() {
        return WeatherProviders.WEATHERBIT;
    }

    @Override
    public LinkedMultiValueMap<String, String> getParametersMap() {
        Map<String, List<String>> stringStringMap = Map.of("lat", Collections.singletonList("21.0122")
                , "lon", Collections.singletonList("52.2297")
                , "key", Collections.singletonList(getApiKey()),
                "include", Collections.singletonList("minutely"));
        return new LinkedMultiValueMap<>(stringStringMap);
    }

    @Override
    public String getPath() {
        return "/v2.0/current/";
    }

}
