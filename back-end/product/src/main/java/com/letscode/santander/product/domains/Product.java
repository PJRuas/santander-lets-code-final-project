package com.letscode.santander.product.domains;

import com.letscode.santander.product.domains.enums.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@Component
@NoArgsConstructor
@Entity
@Table(schema = "product", name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    Category category;

    @Column(name = "price", nullable = false)
    Float price;

    @Column(name = "brand", nullable = false)
    String brand;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Review> reviews;

    @Column(name = "rating")
    Float rating;

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