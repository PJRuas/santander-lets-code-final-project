package com.letscode.santander.orders.domains;

import com.letscode.santander.orders.domains.enums.Status;
import com.letscode.santander.orders.services.converters.HashMapConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Component
@Entity
@NoArgsConstructor
@Table(schema = "`order`", name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Convert(converter = HashMapConverter.class)
    @Column(name = "products", nullable = false)
    private Map<Integer, Integer> products;

    @Column(name = "creation_date")
    @CreatedDate
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    @LastModifiedDate
    private LocalDateTime updateDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status = Status.PROCESSING;
}
