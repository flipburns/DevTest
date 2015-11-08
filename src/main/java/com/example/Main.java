package com.example;

import com.example.model.Customer;
import com.example.model.SortOrder;
import com.example.service.customerservices.CustomerServicesImpl;
import com.example.service.customerservices.CustomerWithNearestNeighbour;
import com.example.service.modelloader.ModelLoaderOMImpl;

import java.util.List;
import java.util.stream.StreamSupport;

public class Main {

    public static ModelLoader modelLoader = new ModelLoaderOMImpl();

    public static CustomerServices customerServices = new CustomerServicesImpl();

    public static void main(String[] args) {

        long timeStart = System.nanoTime();

        //1 - Load the Data into an in memory collection using the ModelLoader.
        Iterable<Customer> customers = modelLoader.loadCustomers();

        //2 - Determine which eye colour is the most popular
        System.out.println("Most Common EyeColour:\n" +
                customerServices.getMostPopularEyeColour(customers).name() + "\n");

        //3 - Output all email addresses sorted alphabetically in ascending order
        System.out.println("\nAll customer emails in ascending order: \n");

        List<Customer> customersSortedByAscEmail = customerServices.getCustomersOrderedByEmail(customers, SortOrder.ASC);
        customersSortedByAscEmail.stream()
                .map(c -> c.getEmail())
                .forEach(System.out::println);

        //4 - Using the above ExecutorService or other form of concurrency, populate the Address field of each customer.
        //    As this is a long running task, we expect some form of parallelism.
        //NOTE: using 'true' here turns it into a parallel stream
        StreamSupport.stream(customers.spliterator(), true)
                .forEach(c -> c.setAddress(
                        customerServices.lookupAddress(c.getLatitude(), c.getLongitude())));

        //5 - Using the above ExecutorService or other form of concurrency, determine which 2 Customers live closest to each other.
        printNearestCustomers(customers);


        //6 - Using the ModuleLoader, write the Customer list back to JSON, including the new Address information
        modelLoader.writeCustomers(customers);


        long timeTakenNanos = System.nanoTime() - timeStart;
        double timeTakenSecs = timeTakenNanos / 1000000000d;
        System.out.println("Time Taken: " + timeTakenSecs + " seconds");


    }

    private static void printNearestCustomers(Iterable<Customer> customers) {
        System.out.println("\nClosest Customers: \n");

        //using a parallel stream to get the customer's nearest neighbour and the distance between them. Then get the minimum distance pair and output.
        CustomerWithNearestNeighbour nearestNeighbours = StreamSupport.stream(customers.spliterator(), true)
                .map(c -> customerServices.findClosestCustomer(c, customers))
                .min((c1, c2) -> c1.getDistanceBetweenNeighbours().compareTo(c2.getDistanceBetweenNeighbours()))
                .get();
        Customer customerOne = nearestNeighbours.getOriginalCustomer();
        Customer customerTwo = nearestNeighbours.getNearestNeighbour();
        System.out.println("Closest customers are " + customerOne.getName() + " and " + customerTwo.getName() + " who have " + nearestNeighbours.getDistanceBetweenNeighbours() + " between them");
    }


}
