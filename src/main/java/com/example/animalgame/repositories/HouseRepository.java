package com.example.animalgame.repositories;

import com.example.animalgame.models.House;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House,Long> {

  Optional<House> findByHouseName(String houseName);

}
