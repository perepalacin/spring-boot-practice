package com.perepalacin.shoppingcart.services.category;

import com.perepalacin.shoppingcart.exceptions.AlreadyExistsException;
import com.perepalacin.shoppingcart.models.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategoryById(Long id);
}
