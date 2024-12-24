package com.apogee.product.services;

import com.apogee.product.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    Product addProduct(Product product);

    Product findProductById(Long productId);
}
