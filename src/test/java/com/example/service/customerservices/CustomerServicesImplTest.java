package com.example.service.customerservices;

import com.example.Main;
import com.example.model.Customer;
import com.example.model.EyeColour;
import com.example.model.SortOrder;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CustomerServicesImplTest {

    private CustomerServicesImpl instance = new CustomerServicesImpl();

    @Test
    public void shouldGetMostCommonEyeColour(){
        Iterable<Customer> customers = asList(
                generateCustomerWithEyes(EyeColour.BLUE),
                generateCustomerWithEyes(EyeColour.BLUE),
                generateCustomerWithEyes(EyeColour.BLUE),
                generateCustomerWithEyes(EyeColour.BROWN),
                generateCustomerWithEyes(EyeColour.BROWN),
                generateCustomerWithEyes(EyeColour.GREEN)
        );

        EyeColour mostPopularEyeColour = instance.getMostPopularEyeColour(customers);
        assertThat(mostPopularEyeColour , is(EyeColour.BLUE));
    }

    @Test
    public void shouldSortByEmailAscAndDesc(){
        Iterable<Customer> customers = asList(
                generateCustomerWithEmail("a@a.com"),
                generateCustomerWithEmail("b@b.com"),
                generateCustomerWithEmail("z@z.com")
        );

        List<Customer> customersOrderedByEmail = instance.getCustomersOrderedByEmail(customers, SortOrder.ASC);
        Customer first = customersOrderedByEmail.get(0);
        Customer last = customersOrderedByEmail.get(customersOrderedByEmail.size()-1);
        assertThat(first.getEmail(), is("a@a.com"));
        assertThat(last.getEmail(), is("z@z.com"));

        customersOrderedByEmail = instance.getCustomersOrderedByEmail(customers, SortOrder.DESC);
        first = customersOrderedByEmail.get(0);
        last = customersOrderedByEmail.get(customersOrderedByEmail.size()-1);
        assertThat(first.getEmail(), is("z@z.com"));
        assertThat(last.getEmail(), is("a@a.com"));
    }

    private Customer generateCustomerWithEmail(String email) {
        Customer customer = new Customer();
        customer.setEmail(email);
        return customer;
    }

    private Customer generateCustomerWithEyes(EyeColour colour) {
        Customer customer = new Customer();
        customer.setEyeColor(colour.name());
        return customer;
    }

    @Test
    public void shouldGetDistanceBetweenPoints(){
        Customer one = new Customer();
        one.setLatitude(1d);
        one.setLongitude(1d);
        Customer two = new Customer();
        two.setLatitude(3d);
        two.setLongitude(3d);

        Double distanceBetweenCustomers = instance.getDistanceBetweenCustomers(two, one);

        //doubles are unreliable so we'll check a range
        assertThat(distanceBetweenCustomers < 2.83d && distanceBetweenCustomers > 2.81d , is(true));

    }

}