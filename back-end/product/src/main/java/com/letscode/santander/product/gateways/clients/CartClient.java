package com.letscode.santander.product.gateways.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("userapi")
public interface CartClient {
    @PostMapping("postProducts")
    public void refreshAvailableProducts();
}
