package com.apogee.product.dto;

import java.util.Date;

public record ProductDto(String shortName, String longName, String description, Date productionDate, Date expireDate, Image image) {
}
