package com.perepalacin.shoppingcart.controller;

import com.perepalacin.shoppingcart.exceptions.ProductNotFoundException;
import com.perepalacin.shoppingcart.response.ApiResponse;
import com.perepalacin.shoppingcart.services.cart.ICartService;
import com.perepalacin.shoppingcart.services.cart.item.ICartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/cart-items")
public class CartItemController {
    private final ICartItemService cartItemService;
    private final ICartService cartService;

    public CartItemController(ICartItemService cartItemService, ICartService cartService) {
        this.cartItemService = cartItemService;
        this.cartService = cartService;
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> addItemToCart(
            @RequestParam(required = false) Long cartId,
            @RequestParam Long productId,
            @RequestParam Integer quantity
    ) {
        try {
            if (cartId == null) {
                cartId = cartService.createNewCartAndReturnCartId();
            }
            System.out.println(cartId);
            cartItemService.addItemToCart(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item added successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponse> deleteItemFromCart(
            @RequestParam Long cartId,
            @RequestParam Long itemId
    ) {
        try {
            cartItemService.removeItemFromCart(cartId, itemId);
            return ResponseEntity.ok(new ApiResponse("Removed item successfully", null));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Cart Id not found", null));
        }
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> deleteItemFromCart(
            @RequestParam Long cartId,
            @RequestParam Long itemId,
            @RequestParam int quantity
    ) {
        try {
            cartItemService.updateItemQuantity(cartId, itemId, quantity);
            return ResponseEntity.ok(new ApiResponse("Updated item quantity successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse("Something went wrong please try again later", null));
        }
    }

}
