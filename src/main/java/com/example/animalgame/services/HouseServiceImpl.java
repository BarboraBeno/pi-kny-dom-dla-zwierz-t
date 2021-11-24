package com.example.animalgame.services;

import com.example.animalgame.exceptions.HouseExceptions.HouseNotFoundException;
import com.example.animalgame.exceptions.HouseExceptions.IdRequiredException;
import com.example.animalgame.models.House;
import com.example.animalgame.repositories.HouseRepository;
import java.util.Optional;
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

  @Override
  public House getHouseById(Long id) throws IdRequiredException, HouseNotFoundException {
    if(id == null){
      throw new IdRequiredException("Id is required.");
    }

    Optional<House> house = houseRepository.findById(id);
    if(house.isEmpty()){
      throw new HouseNotFoundException("House not found.");
    }
    return house.get();
  }
}
