package com.letscode.santander.users.controllers.controllers;

import com.letscode.santander.users.controllers.responses.CartResponse;
import com.letscode.santander.users.services.CartService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ServiceController {

    @Autowired
    CartService cartService;

    @PostMapping("/postProducts")
    @CircuitBreaker(name = "app-circuitBreak")
    public void refreshProductDb(){
        cartService.checkProducts(true);
    }


    @GetMapping("/cart")
    public List<CartResponse> getAvailableCarts(){
        CartResponse converter = new CartResponse();
        return cartService.getAllNotEmpty().stream().map(converter::fromDomain).collect(Collectors.toList());
    }
}
