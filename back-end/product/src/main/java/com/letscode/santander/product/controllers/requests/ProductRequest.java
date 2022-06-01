package com.letscode.santander.product.controllers.requests;

import com.letscode.santander.product.domains.Product;
import com.letscode.santander.product.domains.enums.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    String name;
    Category category;
    Float price;
    String brand;

    public Product toDomain(){
        Product product = new Product();
        product.setName(this.name);
        product.setCategory(this.category);
        product.setPrice(this.price);
        product.setBrand(this.brand);

        return product;
    }
}