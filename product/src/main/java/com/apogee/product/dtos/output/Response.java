package com.apogee.product.dtos.output;

import com.apogee.product.enums.Status;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class Response {
    private int code;
    private ErrorMessage error;
    private Date timeStamp = new Date();
    private Status status;

    public Response( int code, Status status){
        this.code = code;
        this.status = status;
        this.timeStamp = getTimeStamp();
    }
}
