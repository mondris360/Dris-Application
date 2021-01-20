package com.etranzact.dris.authservice.dris.authservice.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<?> createUser(String email, String password);
}
