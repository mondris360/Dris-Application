package com.etranzact.dris.authservice.dris.authservice.Util;

import com.etranzact.dris.authservice.dris.authservice.Model.User;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorClass.CustomException;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorClass.InvalidInputException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;


import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class JwtToken {
    @Value("${server.servlet.context-path}")
    private String baseRoute;
    // method to generate JWT Token
    public String generateToken(@Valid User user) throws NullPointerException{
        long currentTime =  System.currentTimeMillis();
        // 2hrs
        int tokenValidityPeriod = 2 * 60 * 60 * 1000;
        String apiSecretKey = "AZD452IKDA10DFOSD8547";
        final String generatedToken = Jwts.builder().signWith(SignatureAlgorithm.HS256, apiSecretKey)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + tokenValidityPeriod))
                .claim("email", user.getEmail())
                .claim("Authorities", user.getAuthorities())
                .compact();

        return generatedToken;
    }
}
