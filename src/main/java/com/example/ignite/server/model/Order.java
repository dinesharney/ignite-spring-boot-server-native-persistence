package com.example.ignite.server.model;

import lombok.Data;
import org.apache.ignite.cache.query.annotations.QuerySqlField;
import java.io.Serializable;

@Data
public class Order implements Serializable {
    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField
    private Long customerId;

    @QuerySqlField
    private Double price;

    @QuerySqlField
    private String product;

    // Getters and Setters
}