package com.apogee.product.controllers;

import com.apogee.product.backingService.ProductsBackingService;
import com.apogee.product.dtos.inputs.ProductDto;
import com.apogee.product.dtos.output.AddProductResponseDto;
import com.apogee.product.dtos.output.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path= "/products")
public class ProductController {

    @Autowired private ProductsBackingService productsBackingService;

    @GetMapping
    public ResponseEntity<Response> allProducts(){
    return new ResponseEntity<>(productsBackingService.getAllProducts(),HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public void findProduct(@PathVariable("productId") Long productId){

    }

    @PostMapping
    public ResponseEntity<AddProductResponseDto> addProduct(@RequestBody ProductDto product){
        AddProductResponseDto response = productsBackingService.addProduct(product);
    return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateProduct(@RequestBody Object product){

    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{productId}")
    public void deleteProduct (@PathVariable("productId") Long productId){

    }

}
