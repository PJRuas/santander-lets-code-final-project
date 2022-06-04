package com.letscode.santander.users.controllers.requests;

import com.letscode.santander.users.domains.Cart;
import com.letscode.santander.users.services.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Service
public class CartRequest {
    private Map<Integer, Integer> products = new HashMap<>();
    private Integer userId;
    @Autowired
    UserService userService;

    public Cart toDomain(){
        Cart cart = new Cart();
        cart.setProducts(this.products);
        cart.setOwner(userService.getById(this.userId));

        return cart;
    }
}
