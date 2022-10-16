package com.gabriel.ferreira.moviesbattle.core.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperty {

    public static String CUSTOM_ALGORITHM_PASSWORD;

    @Value("${custom.algorithm.password}")
    public void setCustomAlgorithmPassword(String custom) {
        CUSTOM_ALGORITHM_PASSWORD = custom;
    }

}
