package com.letscode.santander.orders.controllers.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letscode.santander.orders.domains.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class OrderRequest {
    private Integer userId;
    private Integer[][] products;


    public Order toDomain(){
        Order order = new Order();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<Integer, Integer> newMap = new HashMap<>();

        for (Integer[] productAmount : products) {
            newMap.put(productAmount[0], productAmount[1]);
        }
        order.setUserId(this.userId);
        order.setProducts(newMap);

        return order;
    }
}
