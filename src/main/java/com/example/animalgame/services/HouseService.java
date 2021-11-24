package com.example.animalgame.services;

import com.example.animalgame.exceptions.HouseExceptions.HouseNotFoundException;
import com.example.animalgame.exceptions.HouseExceptions.IdRequiredException;
import com.example.animalgame.models.House;

public interface HouseService {

  House createNewHouse(String houseName);

  House getHouseById(Long id) throws IdRequiredException, HouseNotFoundException;

}
