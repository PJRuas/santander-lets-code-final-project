package com.letscode.santander.product.controllers.responses;

import com.letscode.santander.product.domains.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class ReviewResponse {
    private Integer id;
    private Integer productId;
    private String productName;
    private String productBrand;
    private Integer orderId;
    private String content;
    private Float rating;

    public ReviewResponse fromDomain(Review review){
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setId(review.getId());
        reviewResponse.setProductId(review.getProduct().getId());
        reviewResponse.setProductName(review.getProduct().getName());
        reviewResponse.setProductBrand(review.getProduct().getBrand());
        reviewResponse.setOrderId(review.getOrderId());
        reviewResponse.setContent(review.getContent());
        reviewResponse.setRating(review.getRating());

        return reviewResponse;
    }
}
