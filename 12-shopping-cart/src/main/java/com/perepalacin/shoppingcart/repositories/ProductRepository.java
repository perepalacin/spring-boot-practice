package com.perepalacin.shoppingcart.repositories;

import com.perepalacin.shoppingcart.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //WE add category name since category is an entity on its own (it has it's own table)
    List<Product> findByCategoryName(String category); //Product == entity, Long == id type of product.

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    List<Product> findByName(String name);

    List<Product> findByBrandAndName(String brand, String name);

    Long countByBrandAndName(String brand, String name);

    Long countByBrand(String brand);
}
