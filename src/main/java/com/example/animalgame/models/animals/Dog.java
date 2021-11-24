package com.example.animalgame.models.animals;

import com.example.animalgame.models.House;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("Dog")
public class Dog extends Animal {

  public Dog(int level, int hunger, int love, int defence,
      Long startedAt, Long finishedAt, House house) {
    super(null, "dog", level, hunger, love, defence, startedAt, finishedAt, house);
  }
}
