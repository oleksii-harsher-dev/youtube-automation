package com.youtube.appium.utils;

import java.util.Random;

public class RandomUtil {
    public static String randomSearchRequest() {

        Random random = new Random();

        int length = random.nextInt(3) + 2;

        StringBuilder value = new StringBuilder();

        for (int i = 0; i < length; i++) {
            value.append(random.nextInt(10));
        }

        return value.toString();
    }
}
