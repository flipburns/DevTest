package com.example.service.geocode;

import com.example.model.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Service to look up address based on long and latitude using googles geocodes api.
 */
public class GoogleGeocodesService {

    //in production code this might be extracted to a property file or config service
    protected static final String API_KEY = "AIzaSyB9jx7rJCF3qQKbqM4n6I2qn7zLtHCBK3o";

    //in production code we would use a dependency injection framework, however this is overkill for the exercise
    private RestTemplate restTemplate;

    public GoogleGeocodesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieve the street address for a given latitude and longitude using google geocode api
     * @param latitude
     * @param longitude
     * @return address object encapsulating formatted address string
     */
    public Address getAddressFromLatLong(Double latitude, Double longitude){

        String url = getFormattedUrl(latitude.toString(), longitude.toString());

        ResponseEntity<GeocodesResponse> response = restTemplate.getForEntity(url, GeocodesResponse.class);

        String streetAddress = response.getBody().getResults().stream()
                .filter(a -> a.getTypes().contains("street_address"))
                .findFirst()
                .map(a -> a.getFormattedAddress())
                .orElse("Address Not Found");

        return new Address(streetAddress);


    }

    protected String getFormattedUrl(String latitude, String longitude) {
        String baseURL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=%s,%s&key=%s";

        return String.format(baseURL,latitude,longitude,API_KEY);
    }

}
