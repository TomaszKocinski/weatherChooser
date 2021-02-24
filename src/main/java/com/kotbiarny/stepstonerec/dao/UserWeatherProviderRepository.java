package com.kotbiarny.stepstonerec.dao;

import com.kotbiarny.stepstonerec.entity.UserWeatherProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserWeatherProviderRepository extends JpaRepository<UserWeatherProvider, String> {

    Optional<UserWeatherProvider> findByName(String idOfUser);
}
