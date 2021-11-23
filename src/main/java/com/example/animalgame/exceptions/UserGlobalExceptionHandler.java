package com.example.animalgame.exceptions;

import com.example.animalgame.exceptions.UserExceptions.HousenameIsRequiredException;
import com.example.animalgame.exceptions.UserExceptions.IncorrectPasswordException;
import com.example.animalgame.exceptions.UserExceptions.PasswordRequiredException;
import com.example.animalgame.exceptions.UserExceptions.UserNotFoundException;
import com.example.animalgame.exceptions.UserExceptions.UsernameIsTakenException;
import com.example.animalgame.exceptions.UserExceptions.UsernamePasswordRequiredException;
import com.example.animalgame.exceptions.UserExceptions.UsernameRequiredException;
import com.example.animalgame.models.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserGlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Object> userNotFound(UserNotFoundException e) {
    return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UsernamePasswordRequiredException.class)
  public ResponseEntity<Object> usernamePasswordRequired(UsernamePasswordRequiredException e){
    return new ResponseEntity<>(new ErrorDTO(e.getMessage()),HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UsernameRequiredException.class)
  public ResponseEntity<Object> usernameRequired(UsernameRequiredException e){
    return new ResponseEntity<>(new ErrorDTO(e.getMessage()),HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PasswordRequiredException.class)
  public ResponseEntity<Object> passwordRequired(PasswordRequiredException e){
    return new ResponseEntity<>(new ErrorDTO(e.getMessage()),HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UsernameIsTakenException.class)
  public ResponseEntity<Object> usernameIsTaken(UsernameIsTakenException e){
    return new ResponseEntity<>(new ErrorDTO(e.getMessage()),HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HousenameIsRequiredException.class)
  public ResponseEntity<Object> housenameIsRequired(HousenameIsRequiredException e){
    return new ResponseEntity<>(new ErrorDTO(e.getMessage()),HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IncorrectPasswordException.class)
  public ResponseEntity<Object> incorrectPassword(IncorrectPasswordException e){
    return new ResponseEntity<>(new ErrorDTO(e.getMessage()),HttpStatus.BAD_REQUEST);
  }

}
