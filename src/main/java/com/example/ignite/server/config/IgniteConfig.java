package com.example.ignite.server.config;

import com.example.ignite.server.model.Customer;
import com.example.ignite.server.model.Order;
import com.example.ignite.server.model.Product;
import com.example.ignite.server.model.User;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.ignite.server.constants.AppConstants.*;

@Configuration
public class IgniteConfig {

    @Value("${ignite.storage.path}")
    private String storagePath;

    @Value("${ignite.wal.path}")
    private String walPath;

    @Value("${ignite.wal.archive.path}")
    private String walArchivePath;

    @Bean
    public Ignite igniteInstance() {
        IgniteConfiguration cfg = new IgniteConfiguration();

        // Enable native persistence
        DataStorageConfiguration storageCfg = new DataStorageConfiguration();
        storageCfg.setStoragePath(storagePath);
        storageCfg.setWalPath(walPath);
        storageCfg.setWalArchivePath(walArchivePath);

        storageCfg.getDefaultDataRegionConfiguration().setPersistenceEnabled(true);
        cfg.setDataStorageConfiguration(storageCfg);

        // Example cache configuration
        CacheConfiguration<Long, User> userCache = new CacheConfiguration<>(USER_CACHE);
        userCache.setIndexedTypes(Long.class, User.class);
        cfg.setCacheConfiguration(userCache);

        CacheConfiguration<Long, Order> orderCache = new CacheConfiguration<>(ORDER_CACHE);
        orderCache.setIndexedTypes(Long.class, Order.class);
        cfg.setCacheConfiguration(orderCache);

        CacheConfiguration<Long, Product> productCache = new CacheConfiguration<>(PRODUCT_CACHE);
        productCache.setIndexedTypes(Long.class, Product.class);
        cfg.setCacheConfiguration(productCache);

        CacheConfiguration<Long, Customer> customerCache = new CacheConfiguration<>(CUSTOMER_CACHE);
        customerCache.setIndexedTypes(Long.class, Customer.class);
        cfg.setCacheConfiguration(customerCache);

        Ignite ignite = Ignition.start(cfg);
        ignite.active(true); // Activate the cluster for persistence

        return ignite;
    }
}
