package com.apogee.product.backingService;

import com.apogee.product.configs.mappings.Mapper;
import com.apogee.product.dtos.inputs.ProductDto;
import com.apogee.product.dtos.output.AddProductResponseDto;
import com.apogee.product.dtos.output.AllProductsResponseDto;
import com.apogee.product.models.Image;
import com.apogee.product.models.Product;
import com.apogee.product.services.ImageService;
import com.apogee.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsBackingService {

    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Mapper mapper;

    public ProductsBackingService(){
    }

    public AllProductsResponseDto getAllProducts() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        AllProductsResponseDto response = new AllProductsResponseDto();

        List<ProductDto> allProducts =new ArrayList<>();

        for (Product product:productService.findAllProducts() ){
            allProducts.add(mapper.map(product,ProductDto.class));
        }

        response.setProducts(allProducts);
        return response;
    }

    public AddProductResponseDto addProduct(ProductDto productDto) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {

        AddProductResponseDto response;

        Product product = mapper.map(productDto,Product.class);

        Image savedImage = this.imageService.addImage(product.getImage());

        product.setImage(savedImage);
        Product savedProduct = productService.addProduct(product);

        response = mapper.map(savedProduct, AddProductResponseDto.class);

        return response;

    }
}
