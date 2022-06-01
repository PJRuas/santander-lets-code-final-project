package com.letscode.santander.orders.controllers.controllers;

import com.letscode.santander.orders.controllers.requests.OrderRequest;
import com.letscode.santander.orders.controllers.responses.OrderResponse;
import com.letscode.santander.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderResponse converter;

    @GetMapping
    public List<OrderResponse> getAll(){
        return orderService.getAll().stream().map(order -> converter.fromDomain(order)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable Integer id){
        return converter.fromDomain(orderService.getById(id));
    }

    @PostMapping
    public OrderResponse create(@RequestBody OrderRequest orderRequest){
        return converter.fromDomain(orderService.create(orderRequest.toDomain()));
    }

    @PutMapping("/{id}")
    public OrderResponse update(@RequestBody boolean continueOrCancel, @PathVariable Integer id){
        return converter.fromDomain(orderService.update(id, continueOrCancel));
    }

}
