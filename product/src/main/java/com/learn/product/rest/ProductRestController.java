package com.learn.product.rest;

import com.learn.common.domain.Constants;
import com.learn.common.domain.SearchResult;
import com.learn.common.helper.SearchResultsHelper;
import com.learn.product.domain.Product;
import com.learn.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(Constants.PRODUCT_BASE_URL)
public class ProductRestController {

    private static Logger log = LoggerFactory.getLogger(ProductRestController.class.getName());

    @Autowired
    private ProductService productService;


    @RequestMapping(
            method = RequestMethod.GET, path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Product> getProduct(@PathVariable(name = "id") String id) {
        Optional<Product> optionalProduct = productService.getProduct(id);
        return optionalProduct.map(p -> ResponseEntity.ok(p)).orElse(ResponseEntity.badRequest().build());
    }


    @RequestMapping(
            method = RequestMethod.POST, path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product createdProduct = productService.createProduct(product);
            URI uri = new URI(Constants.PRODUCT_BASE_URL + "/" + createdProduct.getId());
            return ResponseEntity.created(uri).body(createdProduct);
        } catch (Exception e) {
            log.error("Error creating a new product", e);
            return ResponseEntity.badRequest().build();
        }
    }



    @RequestMapping(
            method = RequestMethod.PUT, path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@PathVariable(name = "id")String productId, @RequestBody Product product) {
        try {
            product.setId(productId);
            Product updatedProduct = productService.updateProduct(product);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            log.error("Error updating product : " + productId, e);
            return ResponseEntity.badRequest().build();
        }
    }


    @RequestMapping(
            method = RequestMethod.GET, path = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchResult> searchProductsByName(
            @RequestParam(name = "pageNum", required = false) String pageNum,
            @RequestParam(name = "pageSize", required = false) String pageSize,
            @RequestParam(name = "name", required = false) String name) {

        try {
            int page = Optional.ofNullable(pageNum).map(p -> Integer.parseInt(p)).orElse(0);
            int size = Optional.ofNullable(pageSize).map(s -> Integer.parseInt(s)).orElse(10);

            Sort sortOrder = Sort.by("name");
            Pageable pageable = PageRequest.of(page, size, sortOrder);
            Page<Product> products = productService.findProductsByName(name, pageable);

            SearchResult searchResult = SearchResultsHelper.convertToSearchResults(products);

            return ResponseEntity.ok(searchResult);
        } catch (Exception e) {
            log.error("Error searching for product", e);
            return ResponseEntity.badRequest().build();
        }
    }

}
