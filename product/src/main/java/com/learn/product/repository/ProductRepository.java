package com.learn.product.repository;

import com.learn.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {


    public Page<Product> findProductsByNameContainingIgnoreCaseOrderByName(String name, Pageable pageable);


}
