package com.etranzact.dris.authservice.dris.authservice.Util;

import com.etranzact.dris.authservice.dris.authservice.Model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtToken {
    private final String apiSecretKey = "AZD452IKDA10DFOSD8547";
    private final int tokenValidityPeriod = 2 * 60 * 60 * 1000; // 2hrs

    // method to generate JWT Token

    public String generateToken(User user){
        // current time in milliseconds
        long currentTime =  System.currentTimeMillis();
        final String generatedToken = Jwts.builder().signWith(SignatureAlgorithm.HS256, apiSecretKey)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + tokenValidityPeriod))
                .claim("email", user.getEmail())
                .claim("Authorities", user.getAuthorities())
                .compact();


        return generatedToken;
    }
}
