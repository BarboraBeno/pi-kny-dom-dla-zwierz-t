package com.example.animalgame.exceptions;

import com.example.animalgame.exceptions.HouseExceptions.HouseNotFoundException;
import com.example.animalgame.exceptions.HouseExceptions.IdRequiredException;
import com.example.animalgame.models.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HouseGlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(HouseNotFoundException.class)
  public ResponseEntity<Object> houseNotFound(HouseNotFoundException e){
    return  new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(IdRequiredException.class)
  public ResponseEntity<Object> idRequiredException(IdRequiredException e){
    return new ResponseEntity<>(new ErrorDTO(e.getMessage()),HttpStatus.BAD_REQUEST);
  }

}
