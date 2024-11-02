package com.apogee.product.backingService;

import com.apogee.product.dtos.inputs.ProductDto;
import com.apogee.product.dtos.output.AddProductResponseDto;
import com.apogee.product.dtos.output.AllProductsResponseDto;
import com.apogee.product.models.Product;
import com.apogee.product.services.ProductService;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsBackingService {

    @Autowired
    private ProductService productService;

    @Autowired
    Mapper mapper;

    public AllProductsResponseDto getAllProducts(){
        AllProductsResponseDto response = new AllProductsResponseDto();
        List<ProductDto> allProducts = productService.findAllProducts().stream().map(product-> mapper.map(product, ProductDto.class)).peek(System.out::println).toList();

        response.setProducts(allProducts);
        return response;
    }

    public AddProductResponseDto addProduct(ProductDto product) {
        AddProductResponseDto response = new AddProductResponseDto();
        Product savedProduct = productService.addProduct(mapper.map(product,Product.class));
        response = mapper.map(savedProduct, AddProductResponseDto.class);
        return response;

    }
}
