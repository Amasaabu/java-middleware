package com.sample.middleware.utils;


import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Utils {
    public String generateId(String idPrefix, int length) {
//        int length = 10;
        StringBuilder string = new StringBuilder();
        Random random = new Random();
        string.append(idPrefix.toString());

        for (int i = 0; i < length - idPrefix.toString().length(); i++) {
            int randomDigit = random.nextInt(10);
            string.append(randomDigit);
        }
        return string.toString();
    }
}
