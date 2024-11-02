package com.apogee.product.dtos.output;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus httpStatus,String errorCode, String errorDescriptionEn,String errorDescriptionAr
                            ) {
}
