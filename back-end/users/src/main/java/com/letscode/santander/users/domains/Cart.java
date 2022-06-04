package com.letscode.santander.users.domains;

import com.letscode.santander.users.services.converters.HashMapConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "`user`", name = "carts")
public class Cart {
    @Id
    @Column(name = "cart_id")
    private Integer id;

    @Convert(converter = HashMapConverter.class)
    @Column(name = "products")
    private Map<Integer, Integer> products = new HashMap<>();

    @Column(name = "total_cost")
    private Float totalCost = 0f;

    @OneToOne(targetEntity = User.class)
    @MapsId
    @JoinColumn(name = "cart_id")
    private User owner;
}
