package com.apogee.product.dtos.output;

import com.apogee.product.entities.ImageEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AddProductResponseDto extends SuccessfulResponse{

    private Long productId;
    private String shortName;
    private String longName;
    private String description;
    private Date productionDate;
    private Date expireDate;
    private ImageDto image;
}
