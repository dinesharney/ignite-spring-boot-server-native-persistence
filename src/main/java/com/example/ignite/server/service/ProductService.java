package com.example.ignite.server.service;

import com.example.ignite.server.model.Product;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.ScanQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.example.ignite.server.constants.AppConstants.PRODUCT_CACHE;

/**
 * Service to handle business logic for Product entities.
 */
@Service
public class ProductService {


    @Autowired
    Ignite ignite;

    public List<Product> getAllProducts() {
        IgniteCache<Long, Product> cache = ignite.getOrCreateCache(PRODUCT_CACHE);
        // Use ScanQuery to retrieve all entries and collect values into a list
        return StreamSupport.stream(
                        cache.query(new ScanQuery<Integer, Product>()).spliterator(), false
                ).map(Cache.Entry::getValue)
                .collect(Collectors.toList());
    }

    public Product getProductById(Long id) {
        IgniteCache<Long, Product> cache = ignite.getOrCreateCache(PRODUCT_CACHE);
        return cache.get(id);
    }

    public Product saveProduct(Product product) {
        IgniteCache<Long, Product> cache = ignite.getOrCreateCache(PRODUCT_CACHE);
        cache.put(product.getId(),product);
        return product;
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        IgniteCache<Long, Product> cache = ignite.getOrCreateCache(PRODUCT_CACHE);
        if (cache.get(id) !=null) {
            cache.put(id,updatedProduct);
            return updatedProduct;
        }
        return null;
    }

    public void deleteProduct(Long id) {
        ignite.getOrCreateCache(PRODUCT_CACHE).remove(id);
    }

}
