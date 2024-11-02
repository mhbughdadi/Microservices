package com.apogee.product.dtos.output;

import com.apogee.product.enums.Status;

public class FailureResponse extends Response {
    private final static int FAILURE_CODE = -1;

    public FailureResponse() {
        super(FAILURE_CODE, Status.FAILURE);
    }
}
