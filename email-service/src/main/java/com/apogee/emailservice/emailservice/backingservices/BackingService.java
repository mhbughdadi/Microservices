package com.apogee.emailservice.emailservice.backingservices;

import com.apogee.emailservice.emailservice.dtos.SendEmailRequest;
import com.apogee.emailservice.emailservice.dtos.response.GenericResponse;
import com.apogee.emailservice.emailservice.enums.Code;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class BackingService {

    private final Logger logger = Logger.getLogger(getClass().getName());

    public GenericResponse sendEmail(SendEmailRequest request ){
        logger.info("request: "+request.toString());

        GenericResponse response = new GenericResponse();
        response.setCode(Code.SUCCESS);
        response.setReason("SUCCESS");

        logger.info("response: " + response.toString());
        return response;
    }
}
