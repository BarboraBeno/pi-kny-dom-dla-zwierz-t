package com.example.animalgame.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import com.example.animalgame.models.others.CustomUserDetails;
import com.example.animalgame.repositories.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceUnitTest {

  private UserServiceImpl userService;
  private JwtService jwtService;
  private UserRepository fakeUserRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  private AuthenticationManager authenticationManager;
  private UserDetailsService userDetailsService;

  @BeforeEach
  void beforeStart(){
    fakeUserRepository = Mockito.mock(UserRepository.class);
    jwtService = new JwtService();
    bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
    authenticationManager = Mockito.mock(AuthenticationManager.class);
    userDetailsService = Mockito.mock(UserDetailsService.class);
    HouseService houseService = Mockito.mock(HouseService.class);
    userService = new UserServiceImpl(fakeUserRepository,bCryptPasswordEncoder,
        houseService,authenticationManager,jwtService,userDetailsService);
}

  @Test
  public void registerSuccess()
      throws HousenameIsRequiredException, UsernameRequiredException, PasswordRequiredException,
      UsernamePasswordRequiredException, UsernameIsTakenException {
    UserRegisterRequestDTO req = new UserRegisterRequestDTO("hana","123456","first");
    House house = new House("first");

    User user = new User(req.getUsername(),req.getPassword(),house);

    when(fakeUserRepository.save(any())).thenReturn(user);

    User result = userService.register(req);

    assertEquals("hana",result.getUsername());
    /*assertEquals("first",result.getHouse().getHouseName());*/
  }

  @Test
  public void registerUsernameAndPasswordRequired(){
    Exception e = assertThrows(UsernamePasswordRequiredException.class,
        () -> userService.register(new UserRegisterRequestDTO("",
            "", "firstHouse")));
    assertEquals("Username and password is required.", e.getMessage());
  }

  @Test
  public void registerUsernameRequired(){
    Exception e = assertThrows(UsernameRequiredException.class,
        () -> userService.register(new UserRegisterRequestDTO("",
            "123456", "firstHouse")));
    assertEquals("Username is required.", e.getMessage());
  }

  @Test
  public void registerPasswordRequired(){
    Exception e = assertThrows(PasswordRequiredException.class,
        () -> userService.register(new UserRegisterRequestDTO("hana",
            "", "firstHouse")));
    assertEquals("Password is required.", e.getMessage());
  }

  @Test
  public void registerHousenameRequired(){
    Exception e = assertThrows(HousenameIsRequiredException.class,
        () -> userService.register(new UserRegisterRequestDTO("hana",
            "123456", "")));
    assertEquals("Housename is required.", e.getMessage());
  }

  @Test
  public void registerUsernameIsAlreadyTaken(){
    UserRegisterRequestDTO req = new UserRegisterRequestDTO("Hana",
        "123456",
        "firstHouse");
    when(fakeUserRepository.findByUsername(any())).thenReturn(
        Optional.of(new User("Hana", null, null)));

    Exception e = assertThrows(UsernameIsTakenException.class,
        () -> userService.register(req));
    assertEquals("Username is already taken.", e.getMessage());
  }

  @Test
  public void loginSuccess()
      throws UserNotFoundException, IncorrectPasswordException, UsernameRequiredException,
      PasswordRequiredException, UsernamePasswordRequiredException {

    String username = "hana";
    String password = "123456";
    House house = new House("firstHouse");
    User user = new User(username, password, house);

    when(fakeUserRepository.findByUsername(any())).thenReturn(
        java.util.Optional.of(new User(username, password, house)));

    when(bCryptPasswordEncoder.matches(password, user.getPassword())).thenReturn(true);

    Mockito.when(fakeUserRepository.findByUsername(any())).thenReturn(
        java.util.Optional.of(user));

    Mockito.when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);

    Mockito.when(userDetailsService.loadUserByUsername(any()))
        .thenReturn(new CustomUserDetails(user));

    String jwt = jwtService.generateToken(new CustomUserDetails(user));
    String result = userService.login(user.getUsername(), user.getPassword());

    assertEquals(jwt, result);
  }

  @Test
  void loginUsernameAndPasswordRequiredException() {
    Exception e = assertThrows(UsernamePasswordRequiredException.class,
        () -> userService.login("", ""));
    assertEquals("Username and password are required.", e.getMessage());
  }

  @Test
  void loginUsernameRequiredException() {
    Exception e = assertThrows(UsernameRequiredException.class,
        () -> userService.login("", "123456"));
    assertEquals("Username is required.", e.getMessage());
  }

  @Test
  void loginPasswordRequiredException() {
    Exception e = assertThrows(PasswordRequiredException.class,
        () -> userService.login("hana", ""));
    assertEquals("Password is required.", e.getMessage());
  }

  @Test
  void loginIncorrectPasswordException() {
    House house = new House("firstHouse");

    when(fakeUserRepository.findByUsername(any())).thenReturn(
        java.util.Optional.of(new User("hana", "123456", house)));

    Exception e = assertThrows(IncorrectPasswordException.class,
        () -> userService.login("hana", "123"));
    assertEquals("Password or username is incorrect.", e.getMessage());
  }

  @Test
  void loginUserNotFound() {
    when(fakeUserRepository.findByUsername(any())).thenReturn(Optional.empty());

    Exception e = assertThrows(UserNotFoundException.class, () -> userService.login("hana", "123"));
    assertEquals("User not found.", e.getMessage());
  }

}
