package com.perepalacin.shoppingcart.services.product;

import com.perepalacin.shoppingcart.models.Product;

import java.util.List;

public class ProductServiceImpl implements IProductService {

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public void updateProduct(Product product, Long id) {

    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategoryName(String category) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByBrandName(String brand) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategoryAndBrandNames(String category, String brand) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return List.of();
    }

    @Override
    public Long countProductsByBrand(String brand) {
        return 0;
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return 0;
    }
}
