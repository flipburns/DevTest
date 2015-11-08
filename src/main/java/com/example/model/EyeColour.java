package com.example.model;

import java.util.Arrays;

/**
 * Enumeration representing EyeColour
 */
public enum EyeColour {

    BLUE("blue"),
    BROWN("brown"),
    GREEN("green"),
    UNKNOWN("unknown");

    private String colour;

    EyeColour(String colour) {
        this.colour = colour;
    }

    public static EyeColour fromValue(String value){
        return Arrays.asList(EyeColour.values())
                .stream()
                .filter(ec -> ec.colour.equalsIgnoreCase(value))
                .findFirst()
                .orElse(EyeColour.UNKNOWN);
    }
}

