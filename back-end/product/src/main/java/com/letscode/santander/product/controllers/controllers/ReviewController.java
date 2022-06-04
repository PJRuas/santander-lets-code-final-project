package com.letscode.santander.product.controllers.controllers;

import com.letscode.santander.product.controllers.requests.ReviewRequest;
import com.letscode.santander.product.controllers.responses.ReviewResponse;
import com.letscode.santander.product.domains.Review;
import com.letscode.santander.product.services.ProductService;
import com.letscode.santander.product.services.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @Autowired
    ProductService productService;

    @Autowired
    ReviewResponse converter;

    @GetMapping
    public List<ReviewResponse> getAllByOrderId(@RequestParam Integer orderId) {
        return reviewService.getAllByOrderId(orderId).stream().map(review -> converter.fromDomain(review)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ReviewResponse getById(@PathVariable Integer id){
        Review review = reviewService.getById(id);
        return converter.fromDomain(review);
    }

    @PostMapping
    public ReviewResponse create(@RequestBody ReviewRequest reviewRequest){
        Review review = (reviewRequest.toDomain());
        review.setProduct(productService.getById(reviewRequest.getProductId()));
        return converter.fromDomain(reviewService.create(review));
    }

    @PutMapping("/{id}")
    public ReviewResponse update(@RequestBody ReviewRequest reviewRequest, @PathVariable Integer id){
        Review reviewToUpdate = reviewRequest.toDomain();
        reviewToUpdate.setProduct(productService.getById(reviewRequest.getProductId()));
        reviewToUpdate.setId(id);
        return converter.fromDomain(reviewService.update(reviewToUpdate));
    }

    @PostMapping("/available")
    public String getAvailable(@RequestBody JSONObject notification){
        log.info(notification.toString());
        return notification.toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        reviewService.delete(id);
    }

}
