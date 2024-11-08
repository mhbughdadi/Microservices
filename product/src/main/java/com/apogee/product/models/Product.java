package com.apogee.product.models;

import com.apogee.product.entities.ImageEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Product {

    private Long productId;
    private String shortName;
    private String longName;
    private String description;
    private Date productionDate;
    private Date expireDate;
    private ImageEntity image;
}
