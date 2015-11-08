package com.example.model;

/**
 * Very simple address data holder - simplified to save time - in a 'real' implementation this would be decomposed into
 * each element e.g. street, postcode, country, etc.
 */
public class Address {

    private String formattedAddress;

    public Address(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}
