package com.gentlemonster.Enum;

public enum SlugUtils {
    ;
    public static String toSlug(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.trim().replaceAll("\\s+", "-").toLowerCase();
    }
}