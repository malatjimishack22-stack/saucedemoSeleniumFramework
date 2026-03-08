package com.saucedemo.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringUtil {

    public static String toTitleCase(String text) {

        if (text == null || text.trim().isEmpty()) {
            return text;
        }

        String[] words = text.toLowerCase().split("\\s+");
        StringBuilder formatted = new StringBuilder();

        for (java.lang.String word : words) {

            java.lang.String[] hyphenParts = word.split("-");

            for (int i = 0; i < hyphenParts.length; i++) {
                hyphenParts[i] = Character.toUpperCase(hyphenParts[i].charAt(0))
                        + hyphenParts[i].substring(1);
            }

            formatted.append(String.join("-", hyphenParts)).append(" ");
        }

        return formatted.toString().trim();
    }

    public static List<String> splitByCharacterToList(String text, char delimiter) {

        if (text == null) {
            return Collections.emptyList();
        }

        return Arrays.asList(
                text.split(java.util.regex.Pattern.quote(String.valueOf(delimiter)))
        );
    }
}
