package com.kotbiarny.stepstonerec.controllers;

import com.kotbiarny.stepstonerec.enums.WeatherProviders;
import com.kotbiarny.stepstonerec.services.WeatherService;
import com.kotbiarny.stepstonerec.services.weatherservices.AccuWeather;
import com.kotbiarny.stepstonerec.services.weatherservices.OpenWeatherMap;
import com.kotbiarny.stepstonerec.services.weatherservices.WeatherBit;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/")
@AllArgsConstructor
public class WeatherController {

    private final WeatherBit weatherBit;
    private final AccuWeather accuWeather;
    private final OpenWeatherMap openWeatherMap;

    private final WeatherService weatherService;

    @GetMapping(value = "/weatherBit")
    public @ResponseBody
    String weatherBit() {
        return weatherBit.getWeather();
    }

    @GetMapping(value = "/accuWeather")
    public @ResponseBody
    String accuWeather() {
        return accuWeather.getWeather();
    }

    @GetMapping(value = "/openWeatherMap")
    public @ResponseBody
    String openWeatherMap() {
        return openWeatherMap.getWeather();
    }

    @GetMapping(value = "/getAllProviderWeather")
    public @ResponseBody
    List<String> getAll() {
        return weatherService.getAllWeatherServices();
    }

    @GetMapping(value = "/getYourProviderWeather")
    public @ResponseBody String getUserWeather(@RequestParam String idOfUser) {
        return weatherService.getYourWeatherService(idOfUser);
    }

    @PutMapping(value = "/chooseProvider")
    public ResponseEntity<Void> chooseProviderPut(@RequestParam String idOfUser, @RequestParam WeatherProviders weatherProviders) {
        weatherService.updateProvider(idOfUser, weatherProviders);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "/chooseProvider")
    public ResponseEntity<Void> chooseProviderPost(@RequestParam String idOfUser, @RequestParam WeatherProviders weatherProviders) {
        weatherService.saveNewProvider(idOfUser, weatherProviders);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getAllProviders")
    public @ResponseBody List<WeatherProviders> getAllProviders(){
        return weatherService.getAllProviders();
    }



}
