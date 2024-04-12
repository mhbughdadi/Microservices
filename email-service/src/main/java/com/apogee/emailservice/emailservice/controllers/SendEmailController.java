package com.apogee.emailservice.emailservice.controllers;

import com.apogee.emailservice.emailservice.backingservices.BackingService;
import com.apogee.emailservice.emailservice.dtos.SendEmailRequest;
import com.apogee.emailservice.emailservice.dtos.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/email")
public class SendEmailController {

    @Autowired
    private BackingService service ;

    @PostMapping("/sendEmail")
    public ResponseEntity<GenericResponse> sendEmail(@RequestBody SendEmailRequest request){

       GenericResponse response = service.sendEmail(request);
       return ResponseEntity.of(Optional.of(response));
    }

}
