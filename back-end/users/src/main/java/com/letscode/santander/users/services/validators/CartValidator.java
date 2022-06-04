package com.letscode.santander.users.services.validators;

import com.letscode.santander.users.controllers.responses.external.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class CartValidator {

    public boolean validateProduct(Integer productId, List<ProductResponse> productsInDb){
        for (ProductResponse product : productsInDb) {
            if (Objects.equals(productId, product.getId())) {return true;}
        }
        return false;
    }
}
