package com.insightsoftware.brewer.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.insightsoftware.brewer.service.exception.NomeEstiloJaCadastradoException;

@RestControllerAdvice
public class ControllerAdviceExceptionHandler {

  @ExceptionHandler(NomeEstiloJaCadastradoException.class)
  public ResponseEntity<String> handleNomeEstiloJaCadastradoException(
      NomeEstiloJaCadastradoException e) {

    return ResponseEntity.badRequest().body(e.getMessage());

  }
}
