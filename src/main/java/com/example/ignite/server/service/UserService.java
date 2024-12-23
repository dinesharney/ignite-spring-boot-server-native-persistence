package com.example.ignite.server.service;

import com.example.ignite.server.model.User;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.ScanQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.example.ignite.server.constants.AppConstants.USER_CACHE;

/**
 * Service to handle business logic for User entities.
 */
@Service
public class UserService {


    @Autowired
    Ignite ignite;

    public List<User> getAllUsers() {
        IgniteCache<Long, User> cache = ignite.getOrCreateCache(USER_CACHE);
        // Use ScanQuery to retrieve all entries and collect values into a list
        return StreamSupport.stream(
                        cache.query(new ScanQuery<Integer, User>()).spliterator(), false
                ).map(Cache.Entry::getValue)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        IgniteCache<Long, User> cache = ignite.getOrCreateCache(USER_CACHE);
        return cache.get(id);
    }

    public User saveUser(User user) {
        IgniteCache<Long, User> cache = ignite.getOrCreateCache(USER_CACHE);
        cache.put(user.getId(),user);
        return user;
    }

    public User updateUser(Long id, User updatedUser) {
        IgniteCache<Long, User> cache = ignite.getOrCreateCache(USER_CACHE);
        if (cache.get(id) !=null) {
            cache.put(id,updatedUser);
            return updatedUser;
        }
        return null;
    }

    public void deleteUser(Long id) {
        ignite.getOrCreateCache(USER_CACHE).remove(id);
    }
}