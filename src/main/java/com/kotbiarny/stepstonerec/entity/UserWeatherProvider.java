package com.kotbiarny.stepstonerec.entity;

import com.kotbiarny.stepstonerec.enums.WeatherProviders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserWeatherProvider {

    @Id
    String name;

    WeatherProviders weatherProviders;
}
