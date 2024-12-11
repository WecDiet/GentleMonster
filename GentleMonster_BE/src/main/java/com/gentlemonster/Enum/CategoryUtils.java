package com.gentlemonster.Enum;

public enum CategoryUtils {
    ;
    public static String toCategory(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.trim().replaceAll("\\s+", "-").toLowerCase();
    }
}
