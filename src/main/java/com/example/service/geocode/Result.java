
package com.example.service.geocode;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    @JsonProperty("formatted_address")
    private String formattedAddress;
    @JsonProperty("types")
    private List<String> types = new ArrayList<String>();


    /**
     * 
     * @return
     *     The formattedAddress
     */
    @JsonProperty("formatted_address")
    public String getFormattedAddress() {
        return formattedAddress;
    }

    /**
     * 
     * @param formattedAddress
     *     The formatted_address
     */
    @JsonProperty("formatted_address")
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }


    /**
     * 
     * @return
     *     The types
     */
    @JsonProperty("types")
    public List<String> getTypes() {
        return types;
    }

    /**
     * 
     * @param types
     *     The types
     */
    @JsonProperty("types")
    public void setTypes(List<String> types) {
        this.types = types;
    }

}
