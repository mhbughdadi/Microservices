package com.apogee.product.backingService;

import com.apogee.product.dtos.output.AllProductsResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductsBackingService {

    public AllProductsResponseDto getAllProducts(){
        AllProductsResponseDto response = new AllProductsResponseDto();
        response.setProducts(new ArrayList<>());
        return response;
    }
}
