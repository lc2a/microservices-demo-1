package com.learn.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document(collection = "product")
public class Product {

    @Id
    private String id;

    @Field(value="product_id")
    @Indexed(unique = true)
    private String productId;

    @Field(value = "name")
    private String name;

    @Field(value = "description")
    private String description;

    @Field(value = "price")
    private float price;

    @CreatedDate
    @Field(value = "created_on")
    @JsonIgnore
    private Date createdOn;

    @LastModifiedDate
    @JsonIgnore
    @Field(value = "modified_on")
    private Date modifiedOn;

}
