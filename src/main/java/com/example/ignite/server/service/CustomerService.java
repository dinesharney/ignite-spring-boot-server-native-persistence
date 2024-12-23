package com.example.ignite.server.service;

import com.example.ignite.server.model.Customer;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.ScanQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.example.ignite.server.constants.AppConstants.CUSTOMER_CACHE;

/**
 * Service to handle business logic for Customer entities.
 */
@Service
public class CustomerService {

    @Autowired
    Ignite ignite;

    public List<Customer> getAllCustomers() {
        {
            IgniteCache<Long, Customer> cache = ignite.getOrCreateCache(CUSTOMER_CACHE);
            // Use ScanQuery to retrieve all entries and collect values into a list
            return StreamSupport.stream(
                            cache.query(new ScanQuery<Integer, Customer>()).spliterator(), false
                    ).map(Cache.Entry::getValue)
                    .collect(Collectors.toList());
        }
    }

    public Customer getCustomerById(Long id) {
        IgniteCache<Long, Customer> cache = ignite.getOrCreateCache(CUSTOMER_CACHE);
        return cache.get(id);
    }

    public Customer saveCustomer(Customer customer) {
        IgniteCache<Long, Customer> cache = ignite.getOrCreateCache(CUSTOMER_CACHE);
        cache.put(customer.getId(),customer);
        return customer;
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        IgniteCache<Long, Customer> cache = ignite.getOrCreateCache(CUSTOMER_CACHE);
        if (cache.get(id) !=null) {
            cache.put(id,updatedCustomer);
            return updatedCustomer;
        }
        return null;
    }

    public void deleteCustomer(Long id) {
        ignite.getOrCreateCache(CUSTOMER_CACHE).remove(id);
    }
}