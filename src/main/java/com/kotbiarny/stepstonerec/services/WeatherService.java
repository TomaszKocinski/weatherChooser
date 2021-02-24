package com.kotbiarny.stepstonerec.services;

import com.kotbiarny.stepstonerec.dao.UserWeatherProviderRepository;
import com.kotbiarny.stepstonerec.entity.UserWeatherProvider;
import com.kotbiarny.stepstonerec.enums.WeatherProviders;
import com.kotbiarny.stepstonerec.services.weatherservices.WeatherClients;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class WeatherService {

    private final List<WeatherClients> weatherBit;
    private final UserWeatherProviderRepository userWeatherProviderRepository;

    public List<String> getAllWeatherServices() {
        return weatherBit.stream()
                .map(WeatherClients::getWeather)
                .collect(Collectors.toList());
    }

    public String getYourWeatherService(String idOfUser) {
        return userWeatherProviderRepository.findByName(idOfUser)
                .map(this::mapToWeatherClientWithFilterOfProvider)
                .map(WeatherClients::getWeather)
                .orElseThrow(() -> new RuntimeException("User Dont Exists"));
    }

    private WeatherClients mapToWeatherClientWithFilterOfProvider(UserWeatherProvider userWeatherProvider) {
        return weatherBit.stream()
                .filter(weatherClients -> weatherClients.getWeatherProviderName() == userWeatherProvider.getWeatherProviders())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Provider saved in DB Dont Exists"));
    }

    public List<WeatherProviders> getAllProviders() {
        return Arrays.asList(WeatherProviders.values().clone());
    }

    public void saveNewProvider(String idOfUser, WeatherProviders weatherProviders) {
        if (userWeatherProviderRepository.findByName(idOfUser).isPresent()) {
            throw new RuntimeException("User already exists, use PUT");
        }
        UserWeatherProvider userWeatherProvider = new UserWeatherProvider();
        userWeatherProviderSave(idOfUser, weatherProviders, userWeatherProvider);
    }

    public void updateProvider(String idOfUser, WeatherProviders weatherProviders) {
        UserWeatherProvider userWeatherProvider = userWeatherProviderRepository.findByName(idOfUser)
                .orElseThrow(() -> new RuntimeException("User dont exists, use POST"));
        userWeatherProviderSave(idOfUser, weatherProviders, userWeatherProvider);
    }

    private void userWeatherProviderSave(String idOfUser, WeatherProviders weatherProviders, UserWeatherProvider userWeatherProvider) {
        userWeatherProvider.setName(idOfUser);
        userWeatherProvider.setWeatherProviders(weatherProviders);
        userWeatherProviderRepository.save(userWeatherProvider);
    }
}
