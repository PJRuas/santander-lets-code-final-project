package com.letscode.santander.users.gateways.clients;

import com.letscode.santander.users.controllers.responses.external.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("productapi")
public interface ProductClient {

    @GetMapping("/product")
    List<ProductResponse> getProducts();

}
