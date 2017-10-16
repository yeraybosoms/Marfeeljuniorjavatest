package com.marfeel.juniortest.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class StaticPostalCodeService implements PostalCodeService {
    private static final Map<String, String> POSTAL_CODES = new HashMap<>();
    private static final String NOT_FOUND = "Not Found";

    static {
        POSTAL_CODES.put("new york", "1");
        POSTAL_CODES.put("washington", "2");
    }

    @Override
    public String getPostalCode(String cityName) {
        return isEmpty(cityName) ? NOT_FOUND : POSTAL_CODES.getOrDefault(cityName.toLowerCase(), NOT_FOUND);
    }
}
