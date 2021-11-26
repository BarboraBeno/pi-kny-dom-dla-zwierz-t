package com.example.animalgame.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TimeServiceImplTest {

  @Test
  void getTimeBetween() {
    TimeServiceImpl timeService = new TimeServiceImpl();
    int result = timeService.getTimeBetween(10L, 20L);
    assertEquals(10, result);
  }
}