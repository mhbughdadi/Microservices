package com.apogee.emailservice.emailservice.dtos.response;

import com.apogee.emailservice.emailservice.enums.Code;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericResponse {

    private Code code;
    private String reason;
    private String description;
    private ErrorResponse errorResponse;

}
