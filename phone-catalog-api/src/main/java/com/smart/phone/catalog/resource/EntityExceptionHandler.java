package com.smart.phone.catalog.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;

/**
 * Created by vmironichev on 10/29/18.
 */
@ControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                           Object body,
                                                           HttpHeaders headers,
                                                           HttpStatus status,
                                                           WebRequest request) {
    var apiError = ApiError.builder().status(HttpStatus.BAD_REQUEST)
            .errors(Collections.singletonList(ex.getMessage()))
            .message(ex.getCause().getMessage())
            .build();
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler(value = RuntimeException.class)
  protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    var error = ApiError.builder()
            .status(HttpStatus.NOT_FOUND)
            .errors(Collections.singletonList(ex.getMessage()))
            .build();
    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  @Builder
  @AllArgsConstructor
  public static class ApiError {
    @Getter private HttpStatus status;
    @Getter private String message;
    @Getter private List<String> errors;
  }
}
