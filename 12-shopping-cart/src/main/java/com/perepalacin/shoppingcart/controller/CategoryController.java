package com.perepalacin.shoppingcart.controller;

import com.perepalacin.shoppingcart.exceptions.AlreadyExistsException;
import com.perepalacin.shoppingcart.exceptions.CategoryNotFoundException;
import com.perepalacin.shoppingcart.models.Category;
import com.perepalacin.shoppingcart.response.ApiResponse;
import com.perepalacin.shoppingcart.services.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getCategories(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) String name) {
        try {
            if (categoryId == null && name == null) {
                List<Category> categories = categoryService.getAllCategories();
                return ResponseEntity.ok(new ApiResponse("Found all categories!", categories));
            } else if (categoryId != null && name == null) {
                Category category = categoryService.getCategoryById(categoryId);
                return ResponseEntity.ok(new ApiResponse("Found category with id:" + categoryId, category));
            } else if (categoryId == null) {
                Category category = categoryService.getCategoryByName(name);
                return ResponseEntity.ok(new ApiResponse("Deleted category with name:" + name, category));
            }
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse("Please send just one query parameter at once", null));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        try {
            Category savedCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Successfully created new category", savedCategory));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/name/{categoryName}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String categoryName) {
        try {
            Category category = categoryService.getCategoryByName(categoryName);
            return ResponseEntity.ok(new ApiResponse("Deleted category with name:" + categoryName, category));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.updateCategory(category, categoryId);
            return ResponseEntity.ok(new ApiResponse("Updated category with id:" + categoryId, updatedCategory));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
        try {
            categoryService.deleteCategoryById(categoryId);
            return ResponseEntity.ok(new ApiResponse("Found category with id:" + categoryId, null));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
