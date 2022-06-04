package com.letscode.santander.users.controllers.responses;

import com.letscode.santander.users.domains.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Service
public class UserResponse {
    Integer id;
    String name;
    String address;
    List<Integer> orders;
    Map<Integer, Integer> cartProducts;
    Float cartCost;


    public UserResponse fromDomain(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setAddress(user.getAddress());
        userResponse.setCartProducts(user.getCart().getProducts());
        userResponse.setCartCost(user.getCart().getTotalCost());
        userResponse.setOrders(user.getOrders());

        return userResponse;
    }
}
