package com.example.ignite.server.service;

import com.example.ignite.server.model.Order;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.ScanQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.example.ignite.server.constants.AppConstants.ORDER_CACHE;
/**
 * Service to handle business logic for Order entities.
 */
@Service
public class OrderService {


        @Autowired
        Ignite ignite;

        public List<Order> getAllOrders() {
        IgniteCache<Long, Order> cache = ignite.getOrCreateCache(ORDER_CACHE);
        // Use ScanQuery to retrieve all entries and collect values into a list
        return StreamSupport.stream(
                        cache.query(new ScanQuery<Integer, Order>()).spliterator(), false
                ).map(Cache.Entry::getValue)
                .collect(Collectors.toList());
    }

        public Order getOrderById(Long id) {
        IgniteCache<Long, Order> cache = ignite.getOrCreateCache(ORDER_CACHE);
        return cache.get(id);
    }

        public Order saveOrder(Order order) {
        IgniteCache<Long, Order> cache = ignite.getOrCreateCache(ORDER_CACHE);
        cache.put(order.getId(),order);
        return order;
    }

        public Order updateOrder(Long id, Order updatedOrder) {
        IgniteCache<Long, Order> cache = ignite.getOrCreateCache(ORDER_CACHE);
        if (cache.get(id) !=null) {
            cache.put(id,updatedOrder);
            return updatedOrder;
        }
        return null;
    }

        public void deleteOrder(Long id) {
        ignite.getOrCreateCache(ORDER_CACHE).remove(id);
    }
}
