package com.letscode.santander.product.services.validator;

import com.letscode.santander.product.domains.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReviewValidator {

    public boolean validateReview (Map<Integer, List<Integer>> available, Review review){
        return available.getOrDefault(review.getOrderId(), new ArrayList<>()).contains(review.getProduct().getId());
    }

}
