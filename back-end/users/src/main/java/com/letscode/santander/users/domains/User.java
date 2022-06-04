package com.letscode.santander.users.domains;

import com.letscode.santander.users.services.converters.ListConverter;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "`user`", name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToOne(targetEntity = Cart.class, mappedBy = "owner", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Cart cart = new Cart();

    @Column(name = "orders")
    @Convert(converter = ListConverter.class)
    private List<Integer> orders = new ArrayList<>();
}
