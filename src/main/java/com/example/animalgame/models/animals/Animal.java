package com.example.animalgame.models.animals;

import com.example.animalgame.models.House;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Animal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long animalId;
  private String type;
  private int level;
  private int hunger;
  private int love;
  private int defence;
  private Long startedAt;
  private Long finishedAt;

  @ManyToOne
  @JoinColumn(name = "houseId")
  private House house;

}
