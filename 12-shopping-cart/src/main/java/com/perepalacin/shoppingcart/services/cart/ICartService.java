package com.perepalacin.shoppingcart.services.cart;

import com.perepalacin.shoppingcart.models.Cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
    Long createNewCartAndReturnCartId();
}
