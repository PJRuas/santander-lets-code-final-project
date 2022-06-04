package com.letscode.santander.product.controllers.responses;

import com.letscode.santander.product.domains.Product;
import com.letscode.santander.product.domains.Review;
import com.letscode.santander.product.domains.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Service
public class ProductResponse {
    private Integer id;
    private String name;
    private Category category;
    private Float price;
    private String brand;
    private Map<Integer,String[]> reviews = new HashMap<>();
    private Float rating;

    public ProductResponse fromDomain(Product product){
        ProductResponse productResponse = new ProductResponse();
        for (Review review: product.getReviews()) {
            productResponse.getReviews().put(review.getId(), new String[]{review.getRating().toString(), review.getContent()});
        }
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setCategory(product.getCategory());
        productResponse.setPrice(product.getPrice());
        productResponse.setBrand(product.getBrand());
        productResponse.setRating(product.getRating());

        return productResponse;
    }
}
