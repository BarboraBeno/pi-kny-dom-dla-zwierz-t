package com.example.animalgame.models.dtos;

import com.example.animalgame.models.House;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterResponseDTO {

  private Long userId;
  private String username;
  private Long houseId;

  public UserRegisterResponseDTO(Long userId, String username, House house) {
    this.userId = userId;
    this.username = username;
    this.houseId = house.getHouseId();
  }
}
