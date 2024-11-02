package com.apogee.product.dtos.output;

import com.apogee.product.enums.Status;

import java.util.Date;

public record Response(int code, ErrorResponse errorResponse, Date timeStamp, Status status) {
}
