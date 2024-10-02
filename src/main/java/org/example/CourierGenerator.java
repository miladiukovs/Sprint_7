package org.example;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

    public static String getRandomData() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;

        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public static Courier getRandom() {
        String login = getRandomData();
        String password = getRandomData();
        String firstName = getRandomData();
        return new Courier(login, password, firstName);
    }

    public static Courier getRandomWithoutLogin() {
        String password = getRandomData();
        String firstName = getRandomData();
        return new Courier(null, password, firstName);
    }
}