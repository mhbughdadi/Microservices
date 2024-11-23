package com.apogee.product.services;

import com.apogee.product.models.Image;
import java.util.List;

public interface ImageService {
    List<Image> findAllImages();

    Image addImage(Image image);
}
