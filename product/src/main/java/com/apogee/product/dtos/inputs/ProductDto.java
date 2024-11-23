package com.apogee.product.dtos.inputs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ProductDto{

    private Long productId;
    private String shortName;
    private String longName;
    private String description;
    private Date productionDate;
    private Date expireDate;
    private ImageInfo image;
}
