package com.apogee.product.controllers;

import com.apogee.product.backingService.ProductsBackingService;
import com.apogee.product.dtos.inputs.ProductDto;
import com.apogee.product.dtos.output.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;
import java.util.ResourceBundle;
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

        AllProductsResponseDto response = productsBackingService.getAllProducts();

        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Response> findProduct(@PathVariable("productId") Long productId) throws Exception{

        FindProductResponseDto response = productsBackingService.getProductById(productId);

        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Response> addProduct(@RequestBody ProductDto product) throws Exception {

        logger.log(Level.SEVERE, "Adding product Request: " + formatAsJsonObject(product));

        AddProductResponseDto response = productsBackingService.addProduct(product);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/products")
    public ResponseEntity<Response> updateProduct(@RequestBody Object product) throws Exception {

        Response response =  this.productsBackingService.updateProduct(product);

        return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{productId}")
    public ResponseEntity<Response> deleteProduct(@PathVariable("productId") Long productId) throws Exception {

        this.productsBackingService.deleteProduct(productId);

        return new ResponseEntity<>(new SuccessfulResponse(), HttpStatus.OK);
    }

}
