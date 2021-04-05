package com.mondris.demo.Service;


import com.mondris.demo.Dto.UserSignUpReqDto;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    void createUser(UserSignUpReqDto request);
//    ResponseEntity<ApiResponse> setUserAuthority(AuthorityReqDto request);

}
