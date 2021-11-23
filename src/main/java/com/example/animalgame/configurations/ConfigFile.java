package com.example.animalgame.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ConfigFile {

  @Bean
  public BCryptPasswordEncoder encrypt() {
    return new BCryptPasswordEncoder();
  }

}
