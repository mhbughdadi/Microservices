package com.apogee.emailservice.emailservice.dtos;

public record SendEmailRequest  (String to, String subject, String body, String[] cc, String [] bcc){

}
