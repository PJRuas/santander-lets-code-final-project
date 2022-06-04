package com.letscode.santander.product.gateways.clients;

import com.letscode.santander.product.controllers.responses.external.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("orderapi")
public interface OrderClient {
    @GetMapping("/nonReviewedOrders")
    List<OrderResponse> fetchOrders();
}
