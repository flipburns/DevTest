package com.example.service.customerservices;

import com.example.model.Customer;

/**
 * Class containing a customer, their closest neighbour, and the distance between them
 * Created by Philip on 08/11/2015.
 */
public class CustomerWithNearestNeighbour {

    private Customer originalCustomer;
    private Customer nearestNeighbour;
    private Double distanceBetweenNeighbours;

    public CustomerWithNearestNeighbour(Customer originalCustomer, Customer nearestNeighbour, Double distanceBetweenNeighbours) {
        this.originalCustomer = originalCustomer;
        this.nearestNeighbour = nearestNeighbour;
        this.distanceBetweenNeighbours = distanceBetweenNeighbours;
    }

    public Customer getOriginalCustomer() {
        return originalCustomer;
    }

    public void setOriginalCustomer(Customer originalCustomer) {
        this.originalCustomer = originalCustomer;
    }

    public Customer getNearestNeighbour() {
        return nearestNeighbour;
    }

    public void setNearestNeighbour(Customer nearestNeighbour) {
        this.nearestNeighbour = nearestNeighbour;
    }

    public Double getDistanceBetweenNeighbours() {
        return distanceBetweenNeighbours;
    }

    public void setDistanceBetweenNeighbours(Double distanceBetweenNeighbours) {
        this.distanceBetweenNeighbours = distanceBetweenNeighbours;
    }
}
