package com.hunghai.emailverifycode.util;

import java.util.Random;

public class Utils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 6;

    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder(LENGTH);
        Random random = new Random();

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateRandomString());
    }
}
