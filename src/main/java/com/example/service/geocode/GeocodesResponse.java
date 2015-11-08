
package com.example.service.geocode;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * CUT DOWN VERSION OF THE FUL GEOCODES RESPONSE FROM GOOGLE
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "results",
    "status"
})
public class GeocodesResponse {

    @JsonProperty("results")
    private List<Result> results = new ArrayList<>();

    /**
     * 
     * @return
     *     The results
     */
    @JsonProperty("results")
    public List<Result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    @JsonProperty("results")
    public void setResults(List<Result> results) {
        this.results = results;
    }

}
