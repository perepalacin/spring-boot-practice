package com.perepalacin.shoppingcart.services.image;

import com.perepalacin.shoppingcart.dto.ImageDto;
import com.perepalacin.shoppingcart.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file, Long imageId);
}
