package com.letscode.santander.product.services;

import com.letscode.santander.product.domains.Review;
import com.letscode.santander.product.gateways.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> getAllByProductId(Integer productId){
        return reviewRepository.findAll().stream().filter(review -> Objects.equals(review.getProduct().getId(), productId)).collect(Collectors.toList());
    };
    public List<Review> getAllByUserId(Integer userId){
        return reviewRepository.findAll().stream().filter(review -> Objects.equals(review.getUserId(), userId)).collect(Collectors.toList());
    };
    public Review create(Review review){
        try {
            reviewRepository.save(review);
        } catch (Exception e) {
            System.out.println(e);
        }
        return  review;
    };
    public Review getById(Integer reviewId){
        return reviewRepository.findById(reviewId).orElseThrow();
    };
    public Review update(Review review){
        Review reviewToUpdate = getById(review.getId());
        reviewToUpdate.setRating(review.getRating());
        reviewToUpdate.setContent(review.getContent());
        return reviewRepository.save(reviewToUpdate);
    };
    public void delete(Integer reviewId){
        var deletable = getById(reviewId);
        reviewRepository.delete(deletable);
    };
}