package com.etranzact.dris.authservice.dris.authservice.Service.Impl;

import com.etranzact.dris.authservice.dris.authservice.Dto.*;
import com.etranzact.dris.authservice.dris.authservice.Model.PreviousPassword;
import com.etranzact.dris.authservice.dris.authservice.Model.User;
import com.etranzact.dris.authservice.dris.authservice.Repository.UserRepository;
import com.etranzact.dris.authservice.dris.authservice.Service.CachingService;
import com.etranzact.dris.authservice.dris.authservice.Service.UserService;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorClass.*;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorClass.IllegalArgumentException;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Response.ApiResponse;
import com.etranzact.dris.authservice.dris.authservice.Util.JwtToken;

import com.etranzact.dris.authservice.dris.authservice.Util.RabbitMQ.AsyncPublisher;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    @Value("${server.servlet.context-path}")
    private String baseRoute;

    @Resource
    private UserRepository userRepository;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private JwtToken jwtToken;

    @Resource
    private AsyncPublisher asyncPublisher;

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private CachingService cachingService;


    @Override
    // method to create a new user
    @CacheEvict(cacheNames = "users", key = "#requestDto.email")
    public ApiResponse createUser(@Valid SignUpRequestDto requestDto) {

        ApiResponse apiResponse;
        String currentRoute = baseRoute + "/user/signUp";
        final User email_address_already_exists = cachingService.getUserByEmail(requestDto.getEmail(), currentRoute, "Email Address Already Exists");
        final User user = modelMapper.map(requestDto, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(requestDto.getPassword()));
        userRepository.save(user);

        String token = jwtToken.generateToken(user);

        asyncPublisher.emailPublisher(user, "Jude", "http://localhost:8080/api/v1/emailVerification/" + token,
                "Email Verification", "emailConfirmation");

        apiResponse = new ApiResponse("Successful", HttpStatus.CREATED, "Email Verification Mail Sent To: "
                + user.getEmail());

        System.out.println("done with createMethod()");
        log.info(Thread.currentThread().getName());

        return apiResponse;
    }


    // method to authenticate a user and return a valid jwt token
    @Override
    @Cacheable(cacheNames = "users-token", key = "#request.email")
    public ApiResponse login(@Valid AuthRequestDto request) {
        String currentRoute = baseRoute + "/users/login";
        ApiResponse apiResponse;
        final User user = cachingService.getUserByEmail(request.getEmail(), currentRoute, "Invalid Login Details");

        if (!user.getEnabled()) {
            throw new UnAuthorizedException("Sorry this  account is not active", currentRoute);
        }

        boolean passwordIsValid = bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword());
        if (!passwordIsValid) {
            throw new CustomException("Invalid Login Details", currentRoute);
        }

        String token = jwtToken.generateToken(user);
        apiResponse = new ApiResponse("Successful", HttpStatus.OK, "Login SuccessFul", token);
        return apiResponse;
    }



    // method to change user password
    @Override
    @CacheEvict(cacheNames = "users", key="#request.getEmail()")
    public ApiResponse changePassword(@Valid ChangePassRequestDto request) {

        ApiResponse apiResponse;
        String currentRoute = baseRoute + "/users/login";

        final User user = userRepository.getByEmail(request.getEmail());
        boolean passwordIsValid = bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword());

        if (!passwordIsValid) {

            throw new UserNotFoundException("Invalid Login Details", currentRoute);

            // if the current password is equal to the new password
        } else if (request.getPassword().equals(request.getNewPassword())) {

            throw new IllegalArgumentException("Your new password must be different from your current password", currentRoute);
            // if the user is changing to a previously used  password

        } else if (isAPreviousPassword(user, request.getNewPassword())) {

            throw new IllegalArgumentException("You cannot  change to a previously used password", currentRoute);

        } else {

            String encodedNewPassword = bCryptPasswordEncoder.encode(request.getNewPassword());
            String encodedCurrentPassword = user.getPassword();
            user.setPassword(encodedNewPassword);
            Set<PreviousPassword> data = new HashSet<>();
            data.add(new PreviousPassword(encodedCurrentPassword, user));
            user.setPreviousPasswords(data);
            final User updatedUser = userRepository.save(user);

            apiResponse = new ApiResponse("Successful", HttpStatus.OK, "Password Changed Successfully");

        }

        return apiResponse;
    }


    // method  to activate or disable user's account (only a user with an HR role can access this method from the user service)
    @Override
    @CacheEvict(cacheNames = "users", key="#request.getEmail()")
    public ApiResponse updateAccountStatus(@Valid AccountStatusDto request) {

        ApiResponse apiResponse;
        String currentRoute = baseRoute + "users/changePassword";

        final User user = cachingService.getUserByEmail(request.getEmail(), currentRoute, "Invalid  Login Details");

        if (user.getEnabled() == request.getIsActivate()) {

            if (user.getEnabled()) {
                throw new IllegalArgumentException("Account is Already Active", currentRoute);
            }

            throw new IllegalArgumentException("Account is Already Inactive", currentRoute);

        } else {

            String typeOfUpdate = request.getIsActivate() ? "Activation" : "Deactivation";
            user.setEnabled(request.getIsActivate());
            userRepository.save(user);
            apiResponse = new ApiResponse("Successful", HttpStatus.OK, "Account " + typeOfUpdate + " Was " + " Successful");
        }

        return apiResponse;
    }


    // method to verify user email  and activate the account
    @Override
    public ResponseEntity<ApiResponse> emailVerification(String emailToken) throws Exception {

        String currentPath = "/emailVerification";

        Claims claims = jwtToken.validateToken(emailToken);

        String userEmail = claims.get("email").toString();

        final User user = userRepository.getByEmail(userEmail);

        if (user == null) {
            throw new UserNotFoundException("Invalid Email Address. Please  ReSignUp", currentPath);
        }

        user.setEnabled(true);
        userRepository.save(user);

        ApiResponse apiResponse = new ApiResponse("Successful", HttpStatus.OK, "User Email Was Successfully Verified");

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


    @Override
    public ResponseEntity<ApiResponse> sendNewEmailVerificationLink(String email) throws InterruptedException {

        String currentRoute = "/sendNewEmailVerificationLink";

        final User user = cachingService.getUserByEmail(email, currentRoute, "Invalid  Login Details");


        String token = jwtToken.generateToken(user);

        asyncPublisher.emailPublisher(user, "No Name", "http://localhost:8080/api/v1/emailVerification/" + token,
                "Email Verification", "emailConfirmation");

        ApiResponse apiResponse = new ApiResponse("Successful",
                HttpStatus.CREATED, "Email Verification Mail Sent To: " + user.getEmail());

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());

    }


    // method to check if the user has used  the new password before
    private boolean isAPreviousPassword(User user, String newPassword) {

        return user.getPreviousPasswords().stream()
                .anyMatch(previousPassword -> bCryptPasswordEncoder.matches(newPassword, previousPassword.getNewPassword()));
    }




}
