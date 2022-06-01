package com.letscode.santander.product.controllers.responses;

import com.letscode.santander.product.domains.Product;
import com.letscode.santander.product.domains.Review;
import com.letscode.santander.product.domains.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Service
public class ProductResponse {
    Integer id;
    String name;
    Category category;
    Float price;
    String brand;
    List<Review> reviews;
    Float rating;

    public ProductResponse fromDomain(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setCategory(product.getCategory());
        productResponse.setPrice(product.getPrice());
        productResponse.setBrand(product.getBrand());
        productResponse.setReviews(product.getReviews());
        productResponse.setRating(product.getRating());

        return productResponse;
    }
}
