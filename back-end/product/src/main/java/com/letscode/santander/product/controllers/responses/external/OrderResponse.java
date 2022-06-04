package com.letscode.santander.product.controllers.responses.external;


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
    private String status;

}
