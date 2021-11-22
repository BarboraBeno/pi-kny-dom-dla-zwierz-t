package com.example.animalgame.models.rooms;

import com.example.animalgame.models.House;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public abstract class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long roomId;
  protected String type;
  protected Integer level;
  protected Integer hp;
  protected Long startedAt;
  protected Long finishedAt;

  @ManyToOne
  @JoinColumn(name="house_houseId")
  protected House house;
}
