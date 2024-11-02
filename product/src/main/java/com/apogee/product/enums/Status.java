package com.apogee.product.enums;

public enum Status {
    SUCCESS("0"),
    FAILURE("-1");

    private final String code;

    Status ( String code){
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }

}
