package com.example.animalgame.models.animals;

import com.example.animalgame.models.House;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("Rat")
public class Rat extends Animal{

  public Rat(int level, int hunger, int attack, int defence,
      Long startedAt, Long finishedAt, House house) {
    super(null, "rat", level, hunger, attack, defence, startedAt, finishedAt, house);
  }
}
