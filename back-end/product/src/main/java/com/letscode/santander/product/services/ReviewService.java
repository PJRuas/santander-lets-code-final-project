package com.letscode.santander.product.services;

import com.letscode.santander.product.controllers.responses.external.OrderResponse;
import com.letscode.santander.product.domains.Review;
import com.letscode.santander.product.gateways.ReviewRepository;
import com.letscode.santander.product.gateways.clients.OrderClient;
import com.letscode.santander.product.services.validator.ReviewValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    OrderClient orderClient;

    @Autowired
    ReviewValidator reviewValidator;

    private static final List<OrderResponse> notReviewed = new ArrayList<>();
    public static final Map<Integer,List<Integer>> notReviewedFiltered = new HashMap<>();

    public List<Review> getAllByProductId(Integer productId){
        return reviewRepository.findAll().stream().filter(review -> Objects.equals(review.getProduct().getId(), productId)).collect(Collectors.toList());
    };
    public List<Review> getAllByOrderId(Integer orderId){
        return reviewRepository.findAll().stream().filter(review -> Objects.equals(review.getOrderId(), orderId)).collect(Collectors.toList());
    };

    public List<Review> getAll(){
        return reviewRepository.findAll();
    }

    public Review create(Review review){
        if (reviewValidator.validateReview(notReviewedFiltered, review)) {
            try {
                reviewRepository.save(review);
            } catch (Exception e) {
                System.out.println(e);
            }
            return review;
        }
        Review fakeReview = new Review();
        fakeReview.setContent("PRODUCT ALREADY REVIEWED OR NOT BOUGHT BY THIS USER");
        return fakeReview;
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

    public void checkOrders(boolean shouldFetch){
        if (notReviewedFiltered.isEmpty() || shouldFetch){
            notReviewedFiltered.clear();
            notReviewed.clear();
            notReviewed.addAll(orderClient.fetchOrders());

            List<Review> allReviews = getAll();
            Map<Integer,List<Integer>> alreadyReviewed = new HashMap<>();

            for(Review review : allReviews){
                Integer productId = review.getProduct().getId();
                Integer orderId = review.getOrderId();
                List<Integer> posted = alreadyReviewed.getOrDefault(orderId, new ArrayList<>());
                alreadyReviewed.put(orderId, posted);
            }

            Map<Integer, List<Integer>> newEntry = new HashMap<>();

            for (OrderResponse order : notReviewed) {
                List<Integer> orderProductIds = List.copyOf(order.getProducts().keySet());
                newEntry.put(order.getId(), orderProductIds);
            }

            Map<Integer, List<Integer>> newFiltered = new HashMap<>();
            for (Integer orderId : newEntry.keySet()){
                List<Integer> existentList = alreadyReviewed.getOrDefault(orderId, new ArrayList<>());
                List<Integer> filteredList = newEntry.get(orderId).stream().filter(i -> !existentList.contains(i)).collect(Collectors.toList());
                newFiltered.put(orderId, filteredList);
            }
            notReviewedFiltered.putAll(newFiltered);
        }
    }
}