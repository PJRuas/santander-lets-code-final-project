package com.letscode.santander.orders.controllers.responses.external;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Service
public class CartResponse {
    private Integer id;
    private Map<Integer, Integer> products;
    private Float totalCost;
    private Integer ownerId;
    private String ownerName;
}
