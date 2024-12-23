package com.example.ignite.server.model;

import lombok.Data;
import org.apache.ignite.cache.query.annotations.QuerySqlField;
import java.io.Serializable;

@Data
public class Customer implements Serializable {
    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField
    private String name;

    @QuerySqlField
    private String email;

    // Getters and Setters
}