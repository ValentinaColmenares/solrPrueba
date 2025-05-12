package com.example.solrtest_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<String> handleClientError(HttpClientErrorException e){
    return ResponseEntity
      .status(e.getStatusCode())
      .body(
        "La colección o los parámetros son incorrectos. \n" + e.getResponseBodyAsString()
      );
  }

  @ExceptionHandler(HttpServerErrorException.class)
  public ResponseEntity<String> handleServerError(HttpServerErrorException e){
    return ResponseEntity
      .status(e.getStatusCode())
      .body(
        "Error del servidor de Solr: " + e.getResponseBodyAsString()
      );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericError(Exception e){
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(
        "Error inesperado: " + e.getMessage()
      );
  }

}