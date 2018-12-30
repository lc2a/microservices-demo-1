package com.learn.product.service;

import com.learn.product.domain.Product;
import com.learn.product.repository.ProductRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProduct(String id) {
        return productRepository.findById(id);
    }

    public Page<Product> findProductsByName(String name, Pageable page) {

        if(StringUtils.isEmpty(name)) {
            return productRepository.findAll(page);
        }
        return productRepository.findProductsByNameContainingIgnoreCaseOrderByName(name, page);
    }

}
