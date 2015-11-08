package com.example.service.modelloader;

import com.example.ModelLoader;
import com.example.model.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Object mapper implementation of model loader
 */
public class ModelLoaderOMImpl implements ModelLoader {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Iterable<Customer> loadCustomers() {

        String content = readToStringFromClassPath("data.json");
        List<Customer> customers = new ArrayList();
        try {
            customers = objectMapper.readValue(content, new TypeReference<List<Customer>>() {});
        } catch (IOException e) {
            System.out.println("Error loading data: "+ e.getMessage()); //In production code this would be a logger not a sysout;
        }

        return customers;
    }

    private String readToStringFromClassPath(String filename) {
        try {
            return FileUtils.readFileToString(new File(ClassLoader.getSystemResource(filename).getFile()));
        } catch (IOException e) {
            System.out.println("Error reading file: "+filename); //In production code this would be a logger not a sysout;
            return "";
        }
    }

    @Override
    public int writeCustomers(Iterable<Customer> customers) {
        try {
            File originalFile = new File(ClassLoader.getSystemResource("data.json").getFile());
            File newFile = new File(originalFile.getParentFile(), "enhancedData.json");
            objectMapper.writeValue(newFile, customers);
            System.out.println("New File written to: " +newFile.getAbsolutePath());
            return 1;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return 0;
        }
    }
}
