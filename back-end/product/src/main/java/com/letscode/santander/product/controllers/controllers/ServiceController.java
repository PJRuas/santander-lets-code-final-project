package com.letscode.santander.product.controllers.controllers;

import com.letscode.santander.product.gateways.clients.OrderClient;
import com.letscode.santander.product.services.ReviewService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/postOrders")
    @CircuitBreaker(name = "app-circuitBreak")
    public void refreshAvailableReviews(){
        reviewService.checkOrders(true);
    }
}
