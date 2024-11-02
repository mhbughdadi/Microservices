package com.apogee.product.dtos.output;

import com.apogee.product.enums.Status;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class Response {
    int code;
    ErrorMessage error;
    Date timeStamp = new Date();
    Status status;

    public Response( int code, Status status){
        this.code = code;
        this.status = status;
        this.timeStamp = getTimeStamp();
    }
}
