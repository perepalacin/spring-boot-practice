package com.perepalacin.shoppingcart.controller;

import com.perepalacin.shoppingcart.dto.ProductDto;
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
    public ResponseEntity<ApiResponse> getAllProducts(@RequestParam(required = false) String brand, @RequestParam(required = false) String name, @RequestParam(required = false) String category) {
        try {
            List<Product> products;
            if (brand == null && name == null && category == null) {
                products = productService.getAllProducts();
            } else if (brand != null && name == null && category == null) {
                products = productService.getProductsByBrandName(brand);
            } else if (brand == null && name != null && category == null) {
                products = productService.getProductsByName(name);
            } else if (brand != null && name == null){
                products = productService.getProductsByCategoryAndBrandName(category, brand);
            } else if (category != null) {
                products = productService.getProductsByCategoryName(category);
            } else {
                products = productService.getProductsByBrandAndName(brand, name);
            }
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products were found", null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Fetched all products successfully", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Something went wrong, please try again later", null));
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            ProductDto convertedProduct = productService.convertToProductDto(product);
            return ResponseEntity.ok(new ApiResponse("Fetched product successfully", convertedProduct));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequests product) {
        try {
            Product savedProduct = productService.addProduct(product);
            ProductDto convertedProduct = productService.convertToProductDto(savedProduct);
            return ResponseEntity.ok(new ApiResponse("Added product successfully", convertedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest request, @PathVariable Long productId) {
        try {
            Product updatedProduct = productService.updateProduct(request, productId);
            ProductDto convertedProduct = productService.convertToProductDto(updatedProduct);
            return ResponseEntity.ok(new ApiResponse("Update product successfully!", convertedProduct));
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
}
