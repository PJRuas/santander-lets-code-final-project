package com.letscode.santander.product.controllers.requests;

import com.letscode.santander.product.domains.Review;
import com.letscode.santander.product.services.ProductService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {
    Integer productId;
    Integer userId;
    String content;
    Float rating;

    public Review toDomain(){
        ProductService productService = new ProductService();
        Review review = new Review();
        review.setProduct(productService.getById(this.productId));
        review.setUserId(this.userId);
        review.setContent(this.content);
        review.setRating(this.rating);

        return review;
    }
}
