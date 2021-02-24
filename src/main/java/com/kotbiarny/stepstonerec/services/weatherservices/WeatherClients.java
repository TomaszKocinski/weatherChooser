package com.kotbiarny.stepstonerec.services.weatherservices;

import com.kotbiarny.stepstonerec.enums.WeatherProviders;

public interface WeatherClients {
    String getWeather();

    WeatherProviders getWeatherProviderName();

}
