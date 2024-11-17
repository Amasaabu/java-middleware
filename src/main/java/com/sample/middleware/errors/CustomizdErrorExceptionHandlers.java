package com.sample.middleware.errors;

import com.sample.middleware.models.Responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class CustomizdErrorExceptionHandlers extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequest ex, WebRequest request) {
        var errorResponse = new CustomResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setCode("RR");
//        errorResponse.setDetails(request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        CustomResponse errorResponse = new CustomResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setCode("RR");
//        errorResponse.setDetails(request.getDescription(false))


        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}