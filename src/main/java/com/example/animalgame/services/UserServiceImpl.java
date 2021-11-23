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
import com.example.animalgame.models.others.CustomUserDetails;
import com.example.animalgame.repositories.UserRepository;
import java.util.Optional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final HouseService houseService;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  public UserServiceImpl(UserRepository userRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      HouseService houseService,
      AuthenticationManager authenticationManager,
      JwtService jwtService,
      UserDetailsService userDetailsService) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.houseService = houseService;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
  }

  @Override
  public User createNewUser(String userName, String password, House house) {
    User user = new User(userName, bCryptPasswordEncoder.encode(password), house);
    userRepository.save(user);
    return user;
  }

  @Override
  public boolean isUserPresent(String userName) {
    var optionalUser = userRepository.findByUsername(userName);
    return optionalUser.isPresent();
  }

  @Override
  public User register(UserRegisterRequestDTO req)
      throws UsernamePasswordRequiredException, UsernameRequiredException, PasswordRequiredException,
      UsernameIsTakenException, HousenameIsRequiredException {
    if(req.getUsername().isEmpty() && req.getPassword().isEmpty()){
      throw new UsernamePasswordRequiredException("Username and password is required.");
    } else if(req.getUsername().isEmpty()){
      throw new UsernameRequiredException("Username is required.");
    } else if(req.getPassword().isEmpty()){
      throw new PasswordRequiredException("Password is required.");
    } else if(isUserPresent(req.getUsername())){
      throw new UsernameIsTakenException("Username is already taken.");
    } else if(req.getHouseName().isEmpty()){
      throw new HousenameIsRequiredException("Housename is required.");
    }
    return createNewUser(req.getUsername(),req.getPassword(),
        houseService.createNewHouse(req.getHouseName()));
  }

  @Override
  public String login(String username, String password)
      throws UsernamePasswordRequiredException, UsernameRequiredException, PasswordRequiredException, UserNotFoundException, IncorrectPasswordException {

    if ((username == null || username.isEmpty()) && (password == null || password.isEmpty())) {
      throw new UsernamePasswordRequiredException("Username and password are required.");
    }
    if (username == null || username.isEmpty()) {
      throw new UsernameRequiredException("Username is required.");
    } else if (password == null || password.isEmpty()) {
      throw new PasswordRequiredException("Password is required.");
    }

    Optional<User> optionalUser = userRepository.findByUsername(username);
    if (optionalUser.isEmpty()) {
      throw new UserNotFoundException("User not found.");
    }
    User user = optionalUser.get();
    if (!(bCryptPasswordEncoder.matches(password, user.getPassword())) || !username
        .equals(user.getUsername())) {
      throw new IncorrectPasswordException("Password or username is incorrect.");
    }
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

    final UserDetails userDetails = userDetailsService
        .loadUserByUsername(username);

    return jwtService.generateToken((CustomUserDetails) userDetails);
  }
}
