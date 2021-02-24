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
public class AccuWeather extends AbstarctClient implements WeatherClients {

    @Value("${apiKey.accuweather}")
    public String apiKey;

    public AccuWeather(@Qualifier("accuweather") WebClient webClient) {
        super(webClient);
    }

    @Override
    protected LinkedMultiValueMap<String, String> getParametersMap() {
        Map<String, List<String>> stringStringMap = Map.of("q", Collections.singletonList("21.0122,52.2297")
                , "apikey", Collections.singletonList(getApiKey()));
        return new LinkedMultiValueMap<>(stringStringMap);

    }

    @Override
    protected String getPath() {
        return "locations/v1/cities/geoposition/search";
    }

    @Override
    public WeatherProviders getWeatherProviderName() {
        return WeatherProviders.ACCUWEATHER;
    }
}
