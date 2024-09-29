package com.perepalacin.shoppingcart.services.product;

import com.perepalacin.shoppingcart.exceptions.ProductNotFoundException;
import com.perepalacin.shoppingcart.models.Category;
import com.perepalacin.shoppingcart.models.Product;
import com.perepalacin.shoppingcart.repositories.CategoryRepository;
import com.perepalacin.shoppingcart.repositories.ProductRepository;
import com.perepalacin.shoppingcart.requests.AddProductRequests;
import com.perepalacin.shoppingcart.requests.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequests request) { //Add product request is just the DTO
        //Find if category exists:
        //if so, set it as the new product category
        //If no, set is as a new category
        //Set as new category
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });

        request.setCategory(category);

        return  productRepository.save(createProductHelperMethod(request, category));
    }

    private Product createProductHelperMethod(AddProductRequests product, Category category) {
        return new Product(
                product.getName(),
                product.getBrand(),
                product.getDescription(),
                product.getPrice(),
                product.getInventory(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        () -> {throw new ProductNotFoundException("Product not found!");
                });
    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long id) {
        return productRepository.findById(id)
                .map(existingProduct -> updateExistingProductHelperMethod(existingProduct,request))
                .map(productRepository::save)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    private Product updateExistingProductHelperMethod (Product existingProduct, UpdateProductRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setBrand(request.getBrand());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategoryName(String category) {

        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrandName(String brand) {

        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrandNames(String category, String brand) {

        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrand(String brand) {
        return productRepository.countByBrand(brand);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
