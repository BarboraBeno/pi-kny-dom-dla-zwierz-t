package com.example.animalgame.services;

public interface TimeService {

  long getTime();

  long getTimeAfter(int sec);

  int getTimeBetween(long from, long to);

}
