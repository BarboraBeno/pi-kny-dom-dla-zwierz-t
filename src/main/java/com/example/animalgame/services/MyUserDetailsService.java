package com.example.animalgame.services;

import com.example.animalgame.exceptions.UserExceptions.UserNotFoundException;
import com.example.animalgame.models.User;
import com.example.animalgame.models.others.CustomUserDetails;
import com.example.animalgame.repositories.UserRepository;
import java.util.Optional;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public MyUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @SneakyThrows
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<User> optionalUser = userRepository.findByUsername(username);

    if (optionalUser.isEmpty()) {
      throw new UserNotFoundException("No user found.");
    }
    User user = optionalUser.get();
    return new CustomUserDetails(user);
  }
}
