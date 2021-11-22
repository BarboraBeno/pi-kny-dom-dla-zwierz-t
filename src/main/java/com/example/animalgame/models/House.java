package com.example.animalgame.models;

import com.example.animalgame.models.animals.Animal;
import com.example.animalgame.models.resources.Resource;
import com.example.animalgame.models.rooms.Room;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class House {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long houseId;
  private String houseName;

  @OneToOne(mappedBy = "house")
  private User user;

  @OneToMany(mappedBy = "house")
  private List<Resource> resourceList;

  @OneToMany(mappedBy = "house")
  private List<Animal> animalList;

  @OneToMany(mappedBy = "house")
  private List<Room> roomList;

  public House(String houseName){
    this.houseName = houseName;
    this.resourceList = new ArrayList<>();
    this.animalList = new ArrayList<>();
    this.roomList = new ArrayList<>();
  }
}
