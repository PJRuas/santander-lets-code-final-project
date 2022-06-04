package com.letscode.santander.product.controllers.requests;

import com.letscode.santander.product.domains.Product;
import com.letscode.santander.product.domains.Review;
import com.letscode.santander.product.services.ProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class ReviewRequest {

    private Integer productId;
    private Integer orderId;
    private String content;
    private Float rating;


    public Review toDomain(){
        Review review = new Review();
        review.setProduct(new Product());
        review.setOrderId(this.orderId);
        review.setContent(this.content);
        review.setRating(this.rating);

        return review;
    }
}
