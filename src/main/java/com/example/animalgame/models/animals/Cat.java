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
@DiscriminatorValue("Cat")
public class Cat extends Animal{

  public Cat(int level, int hunger, int love, int defence,
      Long startedAt, Long finishedAt, House house) {
    super(null, "cat", level, hunger, love, defence, startedAt, finishedAt, house);
  }
}
