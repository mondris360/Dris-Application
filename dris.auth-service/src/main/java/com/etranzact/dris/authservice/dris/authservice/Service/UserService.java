package com.etranzact.dris.authservice.dris.authservice.Service;

import com.etranzact.dris.authservice.dris.authservice.Dto.*;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Response.ApiResponse;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.Exceptions;

import javax.validation.constraints.Email;

@Service
public interface UserService {
    ApiResponse createUser(SignUpRequestDto request) throws NotFoundException, Exception;
    ApiResponse login(AuthRequestDto request) ;
    ApiResponse changePassword(ChangePassRequestDto request);
    ApiResponse updateAccountStatus(AccountStatusDto request);
    ResponseEntity<ApiResponse> emailVerification(String emailToken) throws Exception;
    ResponseEntity<ApiResponse> sendNewEmailVerificationLink(String email) throws Exception;
}
