package com.apogee.product.controllers;

import com.apogee.product.backingService.ProductsBackingService;
import com.apogee.product.dtos.inputs.ProductDto;
import com.apogee.product.dtos.output.AddProductResponseDto;
import com.apogee.product.dtos.output.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.apogee.product.utilities.Utilities.formatAsJsonObject;

@RestController
public class ProductController {
    Logger logger = Logger.getLogger(ProductController.class.getName());

    @Autowired
    private ProductsBackingService productsBackingService;

    @GetMapping("/products")
    public ResponseEntity<Response> allProducts() throws Exception {

        return new ResponseEntity<>(productsBackingService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public void findProduct(@PathVariable("productId") Long productId) {

    }

    @PostMapping("/products")
    public ResponseEntity<AddProductResponseDto> addProduct(@RequestBody ProductDto product) throws Exception {

        logger.log(Level.SEVERE, "Adding product Request: " + formatAsJsonObject(product));
        AddProductResponseDto response = productsBackingService.addProduct(product);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/products")
    public void updateProduct(@RequestBody Object product) throws Exception {

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) throws Exception {

    }

}
