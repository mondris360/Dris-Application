package com.etranzact.dris.authservice.dris.authservice.Service.Impl;

import com.etranzact.dris.authservice.dris.authservice.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl  implements UserService {

    @Override
    public ResponseEntity<?> createUser(String email, String password) {
        return null;
    }
}
