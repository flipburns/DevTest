package com.example.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EyeColourTest {

    @Test
    public void shouldGetEnumFromValue(){

        assertThat(EyeColour.fromValue("blue"), is(EyeColour.BLUE));
        assertThat(EyeColour.fromValue("BLUE"), is(EyeColour.BLUE));
        assertThat(EyeColour.fromValue("brown"), is(EyeColour.BROWN));
        assertThat(EyeColour.fromValue("green"), is(EyeColour.GREEN));
        assertThat(EyeColour.fromValue("unspecified"), is(EyeColour.UNKNOWN));

    }

}