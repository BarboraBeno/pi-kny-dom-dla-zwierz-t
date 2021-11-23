package com.example.animalgame.models.others;

import com.example.animalgame.models.House;
import com.example.animalgame.models.User;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

  private final String username;
  private final String password;
  private final Long id;
  private final House house;

  public CustomUserDetails(User user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.id = user.getUserId();
    this.house = user.getHouse();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public Long getUserId(){
    return id;
  }

  public String getHouseName(){
    return house.getHouseName();
  }

  public Long getHouseId(){
    return house.getHouseId();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
