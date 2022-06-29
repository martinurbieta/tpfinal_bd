package com.bd.tpfinal.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { DeliveryException.class, UnexpectedRollbackException.class } )
    protected ResponseEntity<String> handleDeliveryManException(HttpServletRequest request, Exception ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.OK);
    }
}