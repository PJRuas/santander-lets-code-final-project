package com.letscode.santander.users.controllers.responses;

import com.letscode.santander.users.domains.Cart;
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

    public CartResponse fromDomain(Cart cart){
        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(cart.getId());
        cartResponse.setProducts(cart.getProducts());
        cartResponse.setTotalCost(cart.getTotalCost());
        cartResponse.setOwnerId(cart.getOwner().getId());
        cartResponse.setOwnerName(cart.getOwner().getName());

        return cartResponse;
    }
}
