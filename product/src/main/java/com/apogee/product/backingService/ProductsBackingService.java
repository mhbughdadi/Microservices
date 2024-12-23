package com.apogee.product.backingService;

import com.apogee.product.mappings.Mapper;
import com.apogee.product.dtos.inputs.ProductDto;
import com.apogee.product.dtos.output.AddProductResponseDto;
import com.apogee.product.dtos.output.AllProductsResponseDto;
import com.apogee.product.models.Image;
import com.apogee.product.models.Product;
import com.apogee.product.services.ImageService;
import com.apogee.product.services.ProductService;
import com.apogee.product.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsBackingService {

    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Mapper mapper;

    public ProductsBackingService() {
    }

    /**
     * Retrieves all products from the product service and maps them to ProductDto objects.
     *
     * @return AllProductsResponseDto containing a list of all products.
     */
    public AllProductsResponseDto getAllProducts() throws Exception {

        AllProductsResponseDto response = new AllProductsResponseDto();

        List<ProductDto> allProducts = Utilities.transformCollection(productService.findAllProducts(), (product) -> mapper.map(product, ProductDto.class));
        response.setProducts(allProducts);

        return response;
    }

    /**
     * Maps a ProductDto object to a Product object and calls the addProduct method on the product service with it.
     * Also maps the saved product back to an AddProductResponseDto object.
     *
     * @param productDto A ProductDto containing the product information.
     * @return AddProductResponseDto containing the saved product information.
     */
    public AddProductResponseDto addProduct(ProductDto productDto) throws Exception {

        AddProductResponseDto response;

        Product product = mapper.map(productDto, Product.class);

        Product savedProduct = productService.addProduct(product);

        if (savedProduct != null && product.getImages() != null && !product.getImages().isEmpty()){
            product.getImages().forEach(image-> image.setProduct(savedProduct));
        }

        List<Image> savedImages = this.imageService.saveImages(product.getImages());
        if(savedProduct != null) {

            savedProduct.setImages(savedImages);
        }

        response = mapper.map(savedProduct, AddProductResponseDto.class);

        return response;

    }
}
