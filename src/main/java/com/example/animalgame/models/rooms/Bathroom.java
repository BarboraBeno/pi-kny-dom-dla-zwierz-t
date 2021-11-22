package com.example.animalgame.models.rooms;

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
@DiscriminatorValue("bathroom")
public class Bathroom extends Room{

  public Bathroom(Integer level, Integer hp, Long startedAt,
      Long finishedAt, House house) {
    super(null, "bathroom", level, hp, startedAt, finishedAt, house);
  }
}
