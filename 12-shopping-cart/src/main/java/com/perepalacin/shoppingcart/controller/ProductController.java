package com.perepalacin.shoppingcart.controller;

import com.perepalacin.shoppingcart.exceptions.ProductNotFoundException;
import com.perepalacin.shoppingcart.models.Product;
import com.perepalacin.shoppingcart.requests.AddProductRequests;
import com.perepalacin.shoppingcart.requests.UpdateProductRequest;
import com.perepalacin.shoppingcart.response.ApiResponse;
import com.perepalacin.shoppingcart.services.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("Fetched all products successfully", products));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Fetched product successfully", product));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequests product) {
        try {
            Product savedProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Added product successfully", savedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest request, @PathVariable Long productId) {
        try {
            Product updatedProduct = productService.updateProduct(request, productId);
            return ResponseEntity.ok(new ApiResponse("Update product successfully!", updatedProduct));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product to be updated not found", null));
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Deleted product successfully!", null));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product to be updated not found", null));
        }
    }

    @GetMapping("/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
            return ResponseEntity.ok(new ApiResponse("Fetched products successfully", products));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products were found for this brand", null));

        }
    }

}
