package com.example.animalgame.models.resources;

import com.example.animalgame.models.House;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public abstract class Resource {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long resourceId;
  private String type;
  private int amount;
  private int generation;
  private long updatedAt;

  @ManyToOne
  @JoinColumn(name = "house_houseId")
  private House house;

}
