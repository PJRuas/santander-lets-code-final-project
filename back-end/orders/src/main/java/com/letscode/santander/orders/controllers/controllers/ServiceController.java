package com.letscode.santander.orders.controllers.controllers;

import com.letscode.santander.orders.controllers.responses.OrderResponse;
import com.letscode.santander.orders.domains.Order;
import com.letscode.santander.orders.services.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ServiceController {

    @Autowired
    OrderService orderService;

    @PostMapping("/postCarts")
    @CircuitBreaker(name = "app-circuitBreak")
    public void refreshCartDb() {orderService.checkCarts(true);}

    @GetMapping("/nonReviewedOrders")
    public List<OrderResponse> getNonReviewdOrders(){
        OrderResponse orderResponse = new OrderResponse();
        return orderService.getAll().stream().filter(order -> !order.isReviewed()).map(orderResponse::fromDomain).collect(Collectors.toList());
    }
}
