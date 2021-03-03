package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.UserSignUpReqDto;
import com.mondris.demo.Model.Address;
import com.mondris.demo.Model.Employee;
import com.mondris.demo.Model.PhoneContact;
import com.mondris.demo.Repository.UserRepository;
import com.mondris.demo.Service.UserService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    
    @Resource
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<ApiResponse> createUser(UserSignUpReqDto request) {


        Employee user =  modelMapper.map(request, Employee.class);
        Address address =  modelMapper.map(request, Address.class);
        PhoneContact phoneContact =  modelMapper.map(request, PhoneContact.class);
        System.out.println("===================================================");
        System.out.println("user " +  user);
        System.out.println("Address " +  address);
        System.out.println("PhoneContact+ " +  phoneContact);

//        Employee user =  new Employee();
//        user.setEmail(request.getEmail());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//
//        Address address =  new Address();
//        address.setAddressType(request.getAddressType());
//        address.setCity(request.getCity());
//        address.setState(request.getState());
//        address.setCountry(request.getCountry());
//
//        user.setAddress(address);
        return null;

    }
}
