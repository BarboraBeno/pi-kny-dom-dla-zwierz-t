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
@DiscriminatorValue("Money")
public class Money extends Resource {

  public Money(int amount, int generation, long updatedAt,
      House house) {
    super(null, "money", amount, generation, updatedAt, house);
  }
}
