package com.example.animalgame.services;

import com.example.animalgame.models.others.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private final String SECRET_KEY = System.getenv("SECRET_KEY");

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Long extractHouseId(String token) {
    String subToken = token.substring(7);
    return Long.valueOf((Integer) extractAllClaims(subToken).get("houseId"));
  }

  public Long extractUserId(String token) {
    String subToken = token.substring(7);
    return Long.valueOf((Integer) extractAllClaims(subToken).get("userId"));
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public String generateToken(CustomUserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("username", userDetails.getUsername());
    claims.put("userId", userDetails.getUserId());
    claims.put("houseId", userDetails.getHouseId());
    claims.put("houseName", userDetails.getHouseName());
    return createToken(claims, userDetails.getUsername());
  }

  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder().setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
  }

  public Boolean validateToken(String token, CustomUserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

}
