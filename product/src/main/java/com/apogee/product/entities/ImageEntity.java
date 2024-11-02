package com.apogee.product.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ImageEntity {

    @Id
    @GeneratedValue
    private Long imageId;
    private String largeScreen;
    private String smallScreen;
    private String mediumScreen;
    @OneToOne(mappedBy = "image")
    private ProductEntity product;
}
