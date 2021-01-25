package com.etranzact.dris.authservice.dris.authservice.Service;

import com.etranzact.dris.authservice.dris.authservice.Dto.AccountStatusDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.AuthRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.ChangePassRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.SignUpRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Response.ApiResponse;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.Exceptions;

@Service
public interface UserService {
    ApiResponse createUser(SignUpRequestDto request) throws NotFoundException, Exception;
    ApiResponse login(AuthRequestDto request) ;
    ApiResponse changePassword(ChangePassRequestDto request);
    ApiResponse updateAccountStatus(AccountStatusDto request);
}
