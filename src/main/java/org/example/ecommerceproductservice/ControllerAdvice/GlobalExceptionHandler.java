package org.example.ecommerceproductservice.ControllerAdvice;

import org.example.ecommerceproductservice.DTOs.ExceptionDTO;
import org.example.ecommerceproductservice.Exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDTO> handleArithmeticException()
    {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage("Arithmetic exception has happened");
        exceptionDTO.setSolution("I don't know, please try again");

        ResponseEntity<ExceptionDTO> response = new ResponseEntity<>(
          exceptionDTO, HttpStatus.BAD_REQUEST
        );
        return response;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleArrayIndexOutOfBoundsException()
    {
        ResponseEntity<String> response = new ResponseEntity<>(
            "ArrayIndexOutOfBoundsException has happened, Inside the ControllerAdvice.",
                HttpStatus.BAD_REQUEST
        );

        return response;
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException()
    {
        ExceptionDTO exceptionDTO = new ExceptionDTO();

        exceptionDTO.setMessage("Product Not Found");
        exceptionDTO.setSolution("Please try again with a valid product Id");

        ResponseEntity<ExceptionDTO> response = new ResponseEntity<>(
            exceptionDTO, HttpStatus.BAD_REQUEST
        );
        return  response;
    }





}
