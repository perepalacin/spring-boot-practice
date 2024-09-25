package com.perepalacin.shoppingcart.services.product;

import com.perepalacin.shoppingcart.models.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    void updateProduct(Product product, Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategoryName(String category);
    List<Product> getProductsByBrandName(String brand);
    List<Product> getProductsByCategoryAndBrandNames(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrand(String brand);
    Long countProductsByBrandAndName(String brand, String name);
}
