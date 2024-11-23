package com.apogee.product.services.impl;

import com.apogee.product.entities.ImageEntity;
import com.apogee.product.models.Image;
import com.apogee.product.repositories.ImageRepository;
import com.apogee.product.services.ImageService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
    private final ModelMapper mapper;


    public ImageServiceImpl(){
        this.mapper = new ModelMapper();
    }

    @Override
    public List<Image> findAllImages() {

      List<ImageEntity> imageEntities = imageRepository.findAll();

      if(!imageEntities.isEmpty()){
          return imageEntities.stream().map(entity-> mapper.map(entity,Image.class)).toList();
      }else{
          return Collections.emptyList();
      }
    }

    @Override
    public Image addImage(Image image) {

        ImageEntity savedEntity =  imageRepository.save(mapper.map(image,ImageEntity.class));

        return mapper.map(savedEntity,Image.class);
    }

}