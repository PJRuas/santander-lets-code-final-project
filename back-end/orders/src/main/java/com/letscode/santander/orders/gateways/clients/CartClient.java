package com.letscode.santander.orders.gateways.clients;

import com.letscode.santander.orders.controllers.responses.external.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("userapi")
public interface CartClient {

    @GetMapping("/cart")
    public List<CartResponse> getCartsFromDb();

    @DeleteMapping("/user/{userId}/cart")
    public CartResponse clearCart(@PathVariable Integer userId);
}
