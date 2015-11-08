package com.example.service.customerservices;

import com.example.CustomerServices;
import com.example.model.Address;
import com.example.model.Customer;
import com.example.model.EyeColour;
import com.example.model.SortOrder;
import com.example.service.geocode.GoogleGeocodesService;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Philip on 08/11/2015.
 */
public class CustomerServicesImpl implements CustomerServices {

    private GoogleGeocodesService locationService = new GoogleGeocodesService(new RestTemplate());

    @Override
    public EyeColour getMostPopularEyeColour(Iterable<Customer> customers){
        //group customers by eye colour
        Map<String, List<Customer>> customersByEyeColour = StreamSupport.stream(customers.spliterator(), false)
                .collect(Collectors.groupingBy(c -> c.getEyeColor()));

        //find the most commonly occurring colour
        String mostPopularColour = "";
        int max = 0;
        for(String eachEyeColour : customersByEyeColour.keySet()){
            int size = customersByEyeColour.get(eachEyeColour).size();
            if(size > max){
                max = size;
                mostPopularColour = eachEyeColour;
            }
        }
        return EyeColour.fromValue(mostPopularColour);
    }

    @Override
    public List<Customer> getCustomersOrderedByEmail(Iterable<Customer> customers, SortOrder sortOrder) {

        List<Customer> customersSorted = StreamSupport.stream(customers.spliterator(), false)
                .sorted((c1, c2) -> c1.getEmail().compareTo(c2.getEmail()))
                .collect(Collectors.toList());

        if(sortOrder.equals(SortOrder.DESC)){
            Collections.reverse(customersSorted);
        }

        return customersSorted;

    }

    @Override
    public Address lookupAddress(Double latitude, Double longitude) {
        return locationService.getAddressFromLatLong(latitude, longitude);
    }

    @Override
    public CustomerWithNearestNeighbour findClosestCustomer(Customer originalCustomer, Iterable<Customer> customers) {

        CustomerWithNearestNeighbour customerWithNearestNeighbour = StreamSupport.stream(customers.spliterator(), true)
                .filter(c -> !c.equals(originalCustomer)) //remove the
                .map(eachCustomer -> new CustomerWithNearestNeighbour(originalCustomer, eachCustomer, getDistanceBetweenCustomers(eachCustomer, originalCustomer)))
                .min((d1, d2) -> d1.getDistanceBetweenNeighbours().compareTo(d2.getDistanceBetweenNeighbours()))
                .get();

        return customerWithNearestNeighbour;
    }

    protected Double getDistanceBetweenCustomers(Customer customer, Customer inputCustomer) {
        //use pythagoras to get rough distance between points

        Double x1 = customer.getLatitude();
        Double y1 = customer.getLongitude();

        Double x2 = inputCustomer.getLatitude();
        Double y2 = inputCustomer.getLongitude();

        Double x3 = x2 - x1;
        Double y3 = y2 - y1;

        Double xSquared = x3 * x3;
        Double ySquared = y3 * y3;
        double sqrt = Math.sqrt(xSquared + ySquared);

        //this was used while debugging just to ensure that the calculations were correct...
        //System.out.println(customer.getName()+ " and " + inputCustomer.getName() + " have " + sqrt + " between them");

        return sqrt;
    }
}
