package com.perepalacin.shoppingcart.repositories;

import com.perepalacin.shoppingcart.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
