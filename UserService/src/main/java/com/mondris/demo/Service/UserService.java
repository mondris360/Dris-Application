package com.mondris.demo.Service;


import com.mondris.demo.Dto.AuthorityReqDto;
import com.mondris.demo.Dto.UserSignUpReqDto;
import com.mondris.demo.Model.Department;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<ApiResponse> createUser(UserSignUpReqDto request);
//    ResponseEntity<ApiResponse> setUserAuthority(AuthorityReqDto request);

}
