package com.apogee.product.controllers;

import com.apogee.product.dtos.inputs.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path= "/products")
public class ProductController {

    @GetMapping
    public String allProducts(){
    return "all products";
    }

    @GetMapping("/{productId}")
    public void findProduct(@PathVariable("productId") Long productId){

    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto product){

    return product;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateProduct(@RequestBody Object product){

    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{productId}")
    public void deleteProduct (@PathVariable("productId") Long productId){

    }

}
