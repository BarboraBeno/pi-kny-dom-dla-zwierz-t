package com.example.animalgame.services;

import com.example.animalgame.exceptions.UserExceptions.HousenameIsRequiredException;
import com.example.animalgame.exceptions.UserExceptions.IncorrectPasswordException;
import com.example.animalgame.exceptions.UserExceptions.PasswordRequiredException;
import com.example.animalgame.exceptions.UserExceptions.UserNotFoundException;
import com.example.animalgame.exceptions.UserExceptions.UsernameIsTakenException;
import com.example.animalgame.exceptions.UserExceptions.UsernamePasswordRequiredException;
import com.example.animalgame.exceptions.UserExceptions.UsernameRequiredException;
import com.example.animalgame.models.House;
import com.example.animalgame.models.User;
import com.example.animalgame.models.dtos.UserRegisterRequestDTO;

public interface UserService {

  User createNewUser(String userName, String password, House house);

  boolean isUserPresent(String userName);

  User register(UserRegisterRequestDTO req)
      throws UsernamePasswordRequiredException, UsernameRequiredException, PasswordRequiredException, UsernameIsTakenException, HousenameIsRequiredException;

  String login(String username, String password)
      throws UsernamePasswordRequiredException, UsernameRequiredException, PasswordRequiredException, UserNotFoundException, IncorrectPasswordException;

}
