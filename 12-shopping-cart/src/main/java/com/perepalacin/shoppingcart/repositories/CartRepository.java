package com.perepalacin.shoppingcart.repositories;

import com.perepalacin.shoppingcart.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
