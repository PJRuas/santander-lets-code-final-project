package com.letscode.santander.users.gateways.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("orderapi")
public interface OrderClient {

    @PostMapping("/postCarts")
    public void fetchCarts();
}
