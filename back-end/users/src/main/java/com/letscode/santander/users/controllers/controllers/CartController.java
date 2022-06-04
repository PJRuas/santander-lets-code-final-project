package com.letscode.santander.users.controllers.controllers;

import com.letscode.santander.users.controllers.responses.CartResponse;
import com.letscode.santander.users.domains.Cart;
import com.letscode.santander.users.services.CartService;
import com.letscode.santander.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{userId}/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartResponse converter;
    @Autowired
    UserService userService;


    @GetMapping
    public CartResponse getCart(@PathVariable Integer userId){
        return converter.fromDomain(userService.getUserCart(userId));
    }

    @PutMapping
    public CartResponse manageProduct(@RequestParam boolean addOrRemove, @PathVariable Integer userId, @RequestParam Integer productId){
        Integer cartId = userService.getUserCart(userId).getId();
        return converter.fromDomain(cartService.manageItem(addOrRemove, cartId, productId));
    }

    @DeleteMapping
    public CartResponse clearCart(@PathVariable Integer userId){
        return converter.fromDomain(cartService.clearCart(userId));
    }
}