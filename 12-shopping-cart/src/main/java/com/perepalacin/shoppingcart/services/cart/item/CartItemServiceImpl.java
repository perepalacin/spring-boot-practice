package com.perepalacin.shoppingcart.services.cart.item;

import com.perepalacin.shoppingcart.exceptions.ProductNotFoundException;
import com.perepalacin.shoppingcart.models.Cart;
import com.perepalacin.shoppingcart.models.CartItem;
import com.perepalacin.shoppingcart.models.Product;
import com.perepalacin.shoppingcart.repositories.CartItemRepository;
import com.perepalacin.shoppingcart.repositories.CartRepository;
import com.perepalacin.shoppingcart.services.cart.ICartService;
import com.perepalacin.shoppingcart.services.product.IProductService;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements ICartItemService{

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final IProductService productService;
    private final ICartService cartService;

    public CartItemServiceImpl (CartItemRepository cartItemRepository, IProductService productService, ICartService cartService, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
        this.cartService = cartService;
        this.cartRepository = cartRepository;
    }

    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);
        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElse(new CartItem());
        if (cartItem.getId() == null) {
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
        }
        else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        CartItem itemToRemove = this.getCartItem(cartId, productId);
        cart.removeItem(itemToRemove);
        cartRepository.save(cart); // card item is deleted because it becomes orphan
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = cartService.getCart(cartId);
        CartItem itemToUpdateQuantity = this.getCartItem(cartId, productId);
        itemToUpdateQuantity.setQuantity(quantity);
        cart.updateTotalAmount();
        cartItemRepository.save(itemToUpdateQuantity);
        cartRepository.save(cart);
    }

    @Override
    public CartItem getCartItem(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        return cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElseThrow(() -> new ProductNotFoundException("Product with id " + productId + " not found"));
    }
}
