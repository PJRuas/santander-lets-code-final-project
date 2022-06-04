package com.letscode.santander.product.domains;

import com.letscode.santander.product.domains.enums.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
@NoArgsConstructor
@Entity
@Table(schema = "product", name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "brand", nullable = false)
    private String brand;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "rating")
    private Float rating;

    public void calculateRating(){
        Float total = 0f;
        if (!this.reviews.isEmpty()) {
            for (Review review : this.reviews) {
                total += review.getRating();
            }
            setRating(total / this.reviews.size());
        } else {
            setRating(total);
        }
    }
    public Product(String name, Category category, Float price, String brand){
        setName(name);
        setCategory(category);
        setPrice(price);
        setBrand(brand);
    }
}