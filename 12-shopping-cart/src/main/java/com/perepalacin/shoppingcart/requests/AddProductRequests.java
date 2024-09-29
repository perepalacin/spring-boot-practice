package com.perepalacin.shoppingcart.requests;

import com.perepalacin.shoppingcart.models.Category;
import com.perepalacin.shoppingcart.models.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

//THIS CLASS IS JUST A DTO ACTUALLY

@Data //Gives getters and setters
public class AddProductRequests {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;
    private Category category;
}
