package com.example.animalgame.models.resources;


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
@DiscriminatorValue("Food")
public class Food extends Resource {

  public Food(int amount, int generation, long updatedAt,
      House house) {
    super(null, "food", amount, generation, updatedAt, house);
  }
}
