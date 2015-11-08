package com.example;

import com.example.model.Address;
import com.example.model.Customer;
import com.example.model.EyeColour;
import com.example.model.SortOrder;
import com.example.service.customerservices.CustomerWithNearestNeighbour;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

public interface CustomerServices {

    /**
     * NOTE: changed from original api spec to delegate everything to the service instead of returning another list
     * @param customers
     * @return
     */
    public EyeColour getMostPopularEyeColour(Iterable<Customer> customers);

    /**
     *
     * NOTE: changed set to list as set was not required.
     * @param customers
     * @return
     */
    public List<Customer> getCustomersOrderedByEmail(Iterable<Customer> customers, SortOrder sortOrder);

    /**
     * Changed long to Double as long and lat are floating numbers
     * @param latitude
     * @param longitude
     * @return
     */
    public Address lookupAddress(Double latitude, Double longitude);

    /**
     *
     * @param customer
     * @return
     */
    public CustomerWithNearestNeighbour findClosestCustomer(Customer customer, Iterable<Customer> customers);

}
