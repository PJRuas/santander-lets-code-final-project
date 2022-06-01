package com.letscode.santander.product.controllers.controllers;

import com.letscode.santander.product.controllers.requests.ReviewRequest;
import com.letscode.santander.product.controllers.responses.ReviewResponse;
import com.letscode.santander.product.domains.Review;
import com.letscode.santander.product.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewResponse converter;

    @GetMapping
    public List<ReviewResponse> getAllByProductId(@RequestParam Integer productId) {
        return reviewService.getAllByProductId(productId).stream().map(review -> converter.fromDomain(review)).collect(Collectors.toList());
    }

    @GetMapping
    public List<ReviewResponse> getAllByUserId(@RequestParam Integer userId) {
        return reviewService.getAllByUserId(userId).stream().map(review -> converter.fromDomain(review)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ReviewResponse getById(@PathVariable Integer id){
        Review review = reviewService.getById(id);
        return converter.fromDomain(review);
    }

    @PostMapping
    public ReviewResponse create(@RequestBody ReviewRequest reviewRequest){
        Review review = reviewService.create(reviewRequest.toDomain());
        return converter.fromDomain(review);
    }

    @PutMapping("/{id}")
    public ReviewResponse update(@RequestBody ReviewRequest reviewRequest, @PathVariable Integer id){
        Review reviewToUpdate = reviewRequest.toDomain();
        reviewToUpdate.setId(id);
        return converter.fromDomain(reviewService.update(reviewToUpdate));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        reviewService.delete(id);
    }

}
