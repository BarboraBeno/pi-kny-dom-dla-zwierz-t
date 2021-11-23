package com.example.animalgame.services;

import com.example.animalgame.models.House;
import com.example.animalgame.repositories.HouseRepository;
import org.springframework.stereotype.Service;

@Service
public class HouseServiceImpl implements HouseService {

  private final HouseRepository houseRepository;

  public HouseServiceImpl(HouseRepository houseRepository) {
    this.houseRepository = houseRepository;
  }


  @Override
  public House createNewHouse(String houseName) {
    House house = new House(houseName);
    houseRepository.save(house);
    return house;
  }
}
