package com.example.animalgame.controllers;

import com.example.animalgame.exceptions.UserExceptions.HousenameIsRequiredException;
import com.example.animalgame.exceptions.UserExceptions.IncorrectPasswordException;
import com.example.animalgame.exceptions.UserExceptions.PasswordRequiredException;
import com.example.animalgame.exceptions.UserExceptions.UserNotFoundException;
import com.example.animalgame.exceptions.UserExceptions.UsernameIsTakenException;
import com.example.animalgame.exceptions.UserExceptions.UsernamePasswordRequiredException;
import com.example.animalgame.exceptions.UserExceptions.UsernameRequiredException;
import com.example.animalgame.models.User;
import com.example.animalgame.models.dtos.UserRegisterRequestDTO;
import com.example.animalgame.models.dtos.UserRegisterResponseDTO;
import com.example.animalgame.models.others.AuthenticationRequest;
import com.example.animalgame.models.others.AuthenticationResponse;
import com.example.animalgame.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody UserRegisterRequestDTO req)
      throws HousenameIsRequiredException, UsernameRequiredException, PasswordRequiredException,
      UsernamePasswordRequiredException, UsernameIsTakenException {
    User user = userService.register(req);
    return ResponseEntity.status(HttpStatus.CREATED).body(new UserRegisterResponseDTO(user.getUserId(),
        user.getUsername(),user.getHouse()));
  }

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody AuthenticationRequest req)
      throws UserNotFoundException, IncorrectPasswordException, UsernameRequiredException,
      PasswordRequiredException, UsernamePasswordRequiredException {
    String jwt = userService.login(req.getUsername(),req.getPassword());
    return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(jwt,"ok"));
  }
}
