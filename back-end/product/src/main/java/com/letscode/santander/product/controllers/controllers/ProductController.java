package com.letscode.santander.product.controllers.controllers;

import com.letscode.santander.product.controllers.requests.ProductRequest;
import com.letscode.santander.product.controllers.responses.ProductResponse;
import com.letscode.santander.product.domains.Product;
import com.letscode.santander.product.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductResponse converter;

    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAll().stream().map(product -> converter.fromDomain(product)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Integer id){
        Product product = productService.getById(id);
        return converter.fromDomain(product);
    }

    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest productRequest){
        Product product = productRequest.toDomain();
        return converter.fromDomain(productService.create(product));
    }

    @PutMapping("/{id}")
    public ProductResponse update(@RequestBody ProductRequest productRequest, @PathVariable Integer id){
        Product productToUpdate = productRequest.toDomain();
        productToUpdate.setId(id);
        return converter.fromDomain(productService.update(productToUpdate));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        productService.delete(id);
    }

}