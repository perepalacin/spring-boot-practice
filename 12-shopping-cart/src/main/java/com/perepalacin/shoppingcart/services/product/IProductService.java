package com.perepalacin.shoppingcart.services.product;

import com.perepalacin.shoppingcart.dto.ProductDto;
import com.perepalacin.shoppingcart.models.Product;
import com.perepalacin.shoppingcart.requests.AddProductRequests;
import com.perepalacin.shoppingcart.requests.UpdateProductRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequests product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(UpdateProductRequest request, Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategoryName(String category);
    List<Product> getProductsByBrandName(String brand);
    List<Product> getProductsByCategoryAndBrandName(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrand(String brand);
    Long countProductsByBrandAndName(String brand, String name);
    ProductDto convertToProductDto(Product product);
    List<ProductDto> getConvertedProducts(List<Product> products);
}
