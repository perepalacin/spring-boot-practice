package com.perepalacin.shoppingcart.repositories;

import com.perepalacin.shoppingcart.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    boolean existsByName(String name);
}
