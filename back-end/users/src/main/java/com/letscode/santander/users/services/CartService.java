package com.letscode.santander.users.services;

import com.letscode.santander.users.controllers.responses.external.ProductResponse;
import com.letscode.santander.users.domains.Cart;
import com.letscode.santander.users.domains.User;
import com.letscode.santander.users.gateways.CartRepository;
import com.letscode.santander.users.gateways.clients.OrderClient;
import com.letscode.santander.users.gateways.clients.ProductClient;
import com.letscode.santander.users.services.validators.CartValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartValidator validator;

    @Autowired
    ProductClient productClient;

    @Autowired
    OrderClient orderClient;

    public static final List<ProductResponse> availableProducts = new ArrayList<>();

    public Cart getById(Integer id){
        return cartRepository.findById(id).orElseThrow();
    }

    public Cart create(Cart cart){
        return cartRepository.save(cart);
    }

    public List<Cart> getAllNotEmpty(){
        return cartRepository.findAll().stream().filter(cart -> !cart.getProducts().isEmpty()).collect(Collectors.toList());
    }

    public Cart manageItem(boolean addOrRemove, Integer cartId, Integer productId){
        checkProducts(false);
        Cart cart = getById(cartId);
        if (!validator.validateProduct(productId, availableProducts)) {
            User fakeUser = new User();
            Cart fakeCart = new Cart();

            fakeUser.setId(0);
            fakeUser.setName("PRODUCT NOT FOUND");
            fakeCart.setOwner(fakeUser);

            return fakeCart;
        }
        Map<Integer, Integer> productsInCart = cart.getProducts();
        if (addOrRemove){
            productsInCart.put(productId, productsInCart.getOrDefault(productId, 0) + 1);
        } else {
            if (productsInCart.containsKey(productId)) {
                productsInCart.put(productId, productsInCart.get(productId) -1);
                if (productsInCart.get(productId) <= 0) {productsInCart.remove(productId);}
            }
        }
        calculateCost(cart);
        return create(cart);
    }

    public Cart clearCart(Integer cartId){
        Cart cart = getById(cartId);
        cart.getProducts().clear();
        cart.setTotalCost(0f);
        create(cart);
        orderClient.fetchCarts();
        return cart;
    }

    private void calculateCost(Cart cart){
        checkProducts(false);
        Float total = 0f;
        Map<Integer, Integer> cartProducts = cart.getProducts();
        for (Integer key : cartProducts.keySet()){
            for (ProductResponse product : availableProducts) {
                if (Objects.equals(product.getId(), key)){
                    total += product.getPrice() * cartProducts.get(key);
                }
            }
        }
        cart.setTotalCost(total);
    }

    public void checkProducts(boolean shouldFetch){
        if (availableProducts.isEmpty() || shouldFetch){
            availableProducts.clear();
            availableProducts.addAll(productClient.getProducts());
        }
    }
}