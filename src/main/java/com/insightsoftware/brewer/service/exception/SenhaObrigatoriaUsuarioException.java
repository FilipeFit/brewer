package com.insightsoftware.brewer.service.exception;

public class SenhaObrigatoriaUsuarioException extends RuntimeException {

  private static final long serialVersionUID = -4015585462699943956L;

  public SenhaObrigatoriaUsuarioException(String message) {
    super(message);
  }

}
