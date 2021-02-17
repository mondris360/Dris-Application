package com.mondris.demo.Util.SpringSecurity.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class jwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader =  httpServletRequest.getHeader("Authorization");
        String userEmail;
        String jwtToken;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
             jwtToken =  authorizationHeader.substring(7);

        }
    }
}
