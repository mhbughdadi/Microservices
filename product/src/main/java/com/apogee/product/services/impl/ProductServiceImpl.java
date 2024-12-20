package com.apogee.product.services.impl;

import com.apogee.product.entities.ProductEntity;
import com.apogee.product.models.Product;
import com.apogee.product.repositories.ProductRepository;
import com.apogee.product.services.ProductService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    private final ModelMapper mapper;


    public ProductServiceImpl(){
        this.mapper = new ModelMapper();
    }

    @Override
    public List<Product> findAllProducts() {
      List<ProductEntity> productEntities = productRepository.findAll();
      if(!productEntities.isEmpty()){
          return productEntities.stream().map(entity-> mapper.map(entity,Product.class)).toList();
      }else{
          return Collections.emptyList();
      }
    }

    @Override
    public Product addProduct(Product product) {

        ProductEntity savedEntity =  productRepository.save(mapper.map(product,ProductEntity.class));
        return mapper.map(savedEntity,Product.class);
    }
}
