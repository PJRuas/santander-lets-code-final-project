package com.letscode.santander.users.gateways;

import com.letscode.santander.users.domains.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
