package com.example.animalgame.controllers;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.animalgame.models.User;
import com.example.animalgame.models.dtos.UserRegisterRequestDTO;
import com.example.animalgame.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRegisterControllerIntegrationTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  void beforeStart() {
    objectMapper = new ObjectMapper();
  }

  @Test
  @Order(1)
  public void registerSuccessful() throws Exception {
    UserRegisterRequestDTO req = new UserRegisterRequestDTO("hana","123456","firstHouse");

    mockMvc.perform(post("/register")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(req)))
        .andExpect(status().isCreated())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.userId", is(1)))
        .andExpect(jsonPath("$.username", is("hana")))
        .andExpect(jsonPath("$.houseId", is(1)))
        .andDo(print());
  }

  @Test
  @Order(2)
  void registrationUsernameAndPasswordRequired() throws Exception {

    UserRegisterRequestDTO req = new UserRegisterRequestDTO("", "", "Mordor");

    mockMvc.perform(post("/register")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(req)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is("Username and password is required.")))
        .andDo(print());
  }

  @Test
  @Order(3)
  void registrationUsernameRequired() throws Exception {

    UserRegisterRequestDTO req = new UserRegisterRequestDTO("", "12345678", "Mordor");

    mockMvc.perform(post("/register")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(req)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is("Username is required.")))
        .andDo(print());
  }

  @Test
  @Order(4)
  void registrationPasswordRequired() throws Exception {

    UserRegisterRequestDTO req = new UserRegisterRequestDTO("Sauron", "", "Mordor");

    mockMvc.perform(post("/register")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(req)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is("Password is required.")))
        .andDo(print());
  }

  @Test
  @Order(5)
  void registrationUsernameIsTaken() throws Exception {

    UserRegisterRequestDTO req = new UserRegisterRequestDTO("Sauron", "12345678", "Mordor");
    User firstUser = new User("Sauron", "12345678", null);
    userRepository.save(firstUser);

    mockMvc.perform(post("/register")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(req)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is("Username is already taken.")))
        .andDo(print());
  }

  @Test
  @Order(6)
  void registrationHouseNameRequired() throws Exception {

    UserRegisterRequestDTO req = new UserRegisterRequestDTO("Sauron", "12345678", "");

    mockMvc.perform(post("/register")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(req)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is("Housename is required.")))
        .andDo(print());
  }
}
