package com.mondris.demo.Util.SpringSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtToken {

   final  private String SecretKey = "AZD452IKDA10DFOSD8547";

    int tokenValidityPeriod = 2 * 60 * 60 * 1000;

    public String extractEmail(String token) {

        return extractClaim(token, Claims::getSubject);
    }


    public Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }


    public<T> T extractClaim(String token,  Function<Claims, T> claimResolver){

        final Claims claims  = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody();
    }


    private Boolean isTokentExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails){
        String newToken =  Jwts.builder()
                .claim("email", userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date (System.currentTimeMillis()  + tokenValidityPeriod))
                .signWith(SignatureAlgorithm.HS256, SecretKey).compact();
        return newToken;
    }

    // check if the token has expired and if the  email in the token claim,
    // is the same with the user email in the db
    public Boolean validateToken(String token, UserDetails userDetails){
        final String email =  extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokentExpired(token));
    }


}
