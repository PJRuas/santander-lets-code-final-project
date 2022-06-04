package com.letscode.santander.orders.gateways.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("productapi")
public interface ReviewClient {

    @PostMapping("/postOrders")
    public void fetchOrders();
}
