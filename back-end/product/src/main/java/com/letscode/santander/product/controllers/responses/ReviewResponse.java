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
    Integer productId;
    Integer userId;
    String content;
    Float rating;

    public ReviewResponse fromDomain(Review review){
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setProductId(review.getProduct().getId());
        reviewResponse.setUserId(review.getUserId());
        reviewResponse.setContent(review.getContent());
        reviewResponse.setRating(review.getRating());

        return reviewResponse;
    }
}
