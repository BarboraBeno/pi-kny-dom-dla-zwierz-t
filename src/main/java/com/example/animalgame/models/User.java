package com.example.animalgame.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  private String username;
  private String password;
  private String email;

  @OneToOne
  @JoinColumn(name="house_houseId", referencedColumnName = "houseId")
  private House house;

  public User(String username, String password, House house) {
    this.username = username;
    this.password = password;
    this.house = house;
  }
}
