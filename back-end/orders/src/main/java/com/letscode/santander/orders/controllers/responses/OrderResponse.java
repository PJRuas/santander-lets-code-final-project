package com.letscode.santander.orders.controllers.responses;

import com.letscode.santander.orders.domains.Order;
import com.letscode.santander.orders.domains.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Service
public class OrderResponse {
    private Integer id;
    private Integer userId;
    private Map<Integer, Integer> products;
    private Float total;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Status status;

    public OrderResponse fromDomain(Order order){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setProducts(order.getProducts());
        orderResponse.setCreationDate(order.getCreationDate());
        orderResponse.setUpdateDate(order.getUpdateDate());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setTotal(order.getTotal());

        return orderResponse;
    }

}
