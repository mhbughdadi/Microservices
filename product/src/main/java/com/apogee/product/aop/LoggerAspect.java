package com.apogee.product.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static com.apogee.product.utilities.Utilities.formatAsJsonObject;

@Aspect
@Component
public class LoggerAspect {

    Logger logger = Logger.getLogger(LoggerAspect.class.getName());


    @Before("execution(* com.apogee.product.controllers.ProductController.*(..))")
    void before(JoinPoint joinPoint) {

        logger = Logger.getLogger(joinPoint.getSignature().getDeclaringTypeName());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        logger.log(Level.INFO, "URL: " + request.getRequestURL());
        logger.log(Level.INFO, "HTTP Method: " + request.getMethod());
        logger.log(Level.INFO, "Timestamp: " + this.getCurrentTimeStamp());
        logger.log(Level.INFO, "Session ID: " + request.getRequestedSessionId());
        logger.log(Level.INFO, "Path Variables: " + getPathVariables(joinPoint));
        logger.log(Level.INFO, "Query Parameters: " + getQueryParams(request));
        logger.log(Level.INFO, "Headers: " + getHeaders(request));

        // Log request body if present
        if (joinPoint.getArgs().length > 0) {
            logger.log(Level.INFO, "Request Body: " + getRequestBody(joinPoint));
        }

    }

    private String getCurrentTimeStamp() {

        LocalDateTime now = LocalDateTime.now();

        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the timestamp
        return now.format(formatter);
    }

    private String getPathVariables(JoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Map<String, Object> pathVariables = new HashMap<>();

        // Get method parameters and annotations
        Object[] args = joinPoint.getArgs();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        String[] parameterNames = signature.getParameterNames();

        for (int i = 0; i < args.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if (annotation instanceof PathVariable) {
                    String pathVariableName = ((PathVariable) annotation).value();
                    // Use parameter name if @PathVariable name is empty
                    if (pathVariableName.isEmpty()) {
                        pathVariableName = parameterNames[i];
                    }
                    pathVariables.put(pathVariableName, args[i]);
                }
            }
        }

        return formatAsJsonObject(pathVariables);

    }

    private Map<String, String> getQueryParams(HttpServletRequest request) {

        Map<String, String> queryParams = new HashMap<>();

        request.getParameterMap().forEach((key, values) -> queryParams.put(key, String.join(",", values)));

        return queryParams;
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;
    }

    private String getRequestBody(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < args.length; i++) {
            for (Annotation annotation : getParameterizedAnnotations(joinPoint)[i]) {
                if (annotation instanceof RequestBody) {
                    return formatAsJsonObject(args[i]);
                }
            }
        }
        return null;
    }

    private Annotation[][] getParameterizedAnnotations(JoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        return method.getParameterAnnotations();
    }
}
