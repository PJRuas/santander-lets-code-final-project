package com.letscode.santander.product.services;

import com.letscode.santander.product.domains.Product;
import com.letscode.santander.product.gateways.ProductRepository;
import com.letscode.santander.product.gateways.clients.CartClient;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class ProductService {

    @Autowired
    CartClient cartClient;
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    };
    public Product create(Product product){
        try {
            productRepository.save(product);
        } catch (Exception e) {
            System.out.println(e);
        }
        cartClient.refreshAvailableProducts();
        return  product;
    };
    public Product getById(Integer productId){
        return productRepository.findById(productId).orElseThrow();
    };
    public Product update(Product product){
        Product productToUpdate = getById(product.getId());
        if(product.getName() != null){productToUpdate.setName(product.getName());}
        if(product.getCategory() != null) {productToUpdate.setCategory(product.getCategory());}
        if(product.getPrice() != null){productToUpdate.setPrice(product.getPrice());}
        if(product.getBrand() != null ){productToUpdate.setBrand(product.getBrand());}

        productRepository.save(productToUpdate);
        cartClient.refreshAvailableProducts();
        return productToUpdate;
    };
    public void delete(Integer productId){
        var deletable = getById(productId);
        productRepository.delete(deletable);
        cartClient.refreshAvailableProducts();
    };
}
