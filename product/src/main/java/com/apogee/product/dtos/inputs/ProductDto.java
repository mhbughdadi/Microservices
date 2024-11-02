package com.apogee.product.dtos.inputs;

import java.util.Date;

public record ProductDto(String productId, String shortName, String longName, String description, Date productionDate, Date expireDate, ImageInfo image) {
}
