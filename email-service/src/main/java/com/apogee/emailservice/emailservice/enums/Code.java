package com.apogee.emailservice.emailservice.enums;

public enum Code {

    SUCCESS("0"),
    FAILURE("-1");

    private String value ;

    Code(String value ){
        this.value = value;
    }

}
