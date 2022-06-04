package com.letscode.santander.users.controllers.responses.external;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Service
public class ProductResponse {
    private Integer id;
    private String name;
    private String category;
    private Float price;
    private String brand;
    private Map<Integer, String[]> reviews = new HashMap<>();
    private Float rating;
}