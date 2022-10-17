package com.gabriel.ferreira.moviesbattle.core.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperty {

    public static String CUSTOM_ALGORITHM_PASSWORD;

    public static String RAPID_API_IMDB_PUBLIC_KEY;

    @Value("${custom.algorithm.password}")
    public void setCustomAlgorithmPassword(String custom) {
        CUSTOM_ALGORITHM_PASSWORD = custom;
    }

    @Value("${rapid.api.imdb.public.key}")
    public void setRapidApiIMDBPublicKey(String key) {
        RAPID_API_IMDB_PUBLIC_KEY = key;
    }


}
