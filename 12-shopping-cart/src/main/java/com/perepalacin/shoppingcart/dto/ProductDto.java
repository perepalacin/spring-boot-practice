package com.perepalacin.shoppingcart.dto;

import com.perepalacin.shoppingcart.models.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;

    private Category category;

    private List<ImageDto> images;
}
