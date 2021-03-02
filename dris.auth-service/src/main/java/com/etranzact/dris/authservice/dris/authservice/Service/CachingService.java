package com.etranzact.dris.authservice.dris.authservice.Service;

import com.etranzact.dris.authservice.dris.authservice.Model.User;
import com.etranzact.dris.authservice.dris.authservice.Repository.UserRepository;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorClass.UserAlreadyExistsException;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorClass.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@CacheConfig(cacheNames = "users")
public class CachingService {

    @Resource
    private UserRepository userRepository;

    @Value("${server.servlet.context-path}")
    private String baseRoute;

    @Cacheable(key = "#email")
    public User getUserByEmail(String email, String currentPath, String errorMessage){
        final User user = userRepository.getByEmail(email);
        if (currentPath.equals(baseRoute+"/user/signUp") ){
            if (user != null) {
                throw  new UserNotFoundException(errorMessage, currentPath);
            }

        } else if (user == null) {
            throw new UserNotFoundException(errorMessage, currentPath);

        }
        return user;
    }
}
