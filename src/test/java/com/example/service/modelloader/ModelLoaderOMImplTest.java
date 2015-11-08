package com.example.service.modelloader;

import com.example.model.Customer;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class ModelLoaderOMImplTest {

    private ModelLoaderOMImpl instance = new ModelLoaderOMImpl();

    @Test
    public void shouldLoadFileAndMapToObject(){


        Iterable<Customer> customers = instance.loadCustomers();

        assertThat(customers, is(notNullValue()));
        assertThat(customers.iterator().next().getName().getFirst(), is("Whitley"));

    }

    @Test
    public void shouldWriteToFile(){


        Customer customer = new Customer();
        customer.setAbout("testtest");
        Iterable<Customer> customers = asList(customer);
        int result = instance.writeCustomers(customers);

        assertThat(result, is(1));

    }

}