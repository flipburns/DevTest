package com.example.service.geocode;

import com.example.model.Address;
import com.example.service.geocode.GeocodesResponse;
import com.example.service.geocode.GoogleGeocodesService;
import com.example.service.geocode.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GoogleGeocodesServiceTest {

    public static final String FULL_URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=1.0,1.0&key=AIzaSyB9jx7rJCF3qQKbqM4n6I2qn7zLtHCBK3o";
    @Mock
    private RestTemplate mockRestTemplate;

    @InjectMocks
    private GoogleGeocodesService instance;

    @Test
    public void shouldFormatUrlCorrectly(){
        String formattedUrl = instance.getFormattedUrl("1.0", "1.0");
        String expectedUrl = FULL_URL;
        assertThat(formattedUrl, is(expectedUrl));
    }

    @Test
    public void shouldCallRestTemplateAndGetInfo(){

        when(mockRestTemplate.getForEntity(FULL_URL, GeocodesResponse.class))
                .thenReturn(generateMockResponse());

        Address addressFromLatLong = instance.getAddressFromLatLong(1d, 1d);

        assertThat(addressFromLatLong.getFormattedAddress(), is("test address formatted"));


    }

    private ResponseEntity<GeocodesResponse> generateMockResponse() {
        GeocodesResponse response = new GeocodesResponse();
        response.setResults(asList(generateGoodResult()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Result generateGoodResult() {
        Result result = new Result();
        result.setFormattedAddress("test address formatted");
        result.setTypes(asList("street_address"));
        return result;
    }

}