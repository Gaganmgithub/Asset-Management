package com.example.asset_management.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends Throwable {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
//        String errorMessage = ex.getMessage();
//        // Check if the error message contains information about the serialNumber constraint violation
//        if (errorMessage.contains("serialNumber")) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Duplicate serialNumber detected");
//        }
//        // For other data integrity violations, return a generic error message
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Data integrity violation occurred");
//    }
}
