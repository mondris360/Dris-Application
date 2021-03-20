package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.AuthorityReqDto;
import com.mondris.demo.Dto.UserSignUpReqDto;
import com.mondris.demo.Dto.UserSignUpResponseDto;
import com.mondris.demo.Model.*;
import com.mondris.demo.Repository.*;
import com.mondris.demo.Service.UserService;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.UserNotFoundException;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private AddressRepository addressRepository;

    @Resource
    private CityRepository cityRepository;

    @Resource
    private StateRepository stateRepository;

    @Resource
    private CountryRepository countryRepository;


    @Resource
    private PhoneContactRepository phoneContactRepository;
    
    @Resource
    private ModelMapper modelMapper;


    @Override
    public ResponseEntity<ApiResponse> createUser(UserSignUpReqDto request) {

        Country country;

        State state;

        City city;

        ApiResponse apiResponse;

        String currentPath="/completeSignUp";

        Employee userExists =  userRepository.getByEmail(request.getEmail());

        if(userExists != null){
            throw new UserNotFoundException("A User With This Email Already Exists", HttpStatus.BAD_REQUEST, currentPath);
        }

        Employee newUser =  modelMapper.map(request, Employee.class);
        final Employee user = userRepository.save(newUser);

        // create country if it does not exist
        String countryName =  request.getCountry().toLowerCase().trim();
        String stateName =  request.getState().toLowerCase().trim();
        String cityName =  request.getCity().toLowerCase().trim();


        country = countryRepository.getByName(countryName);
        if(country == null){
            country  =  new Country(request.getCountry().toLowerCase().trim(), request.getZipCode());
            countryRepository.save(country);
        }

        // create state if it does not exist
        state =  stateRepository.getByName(stateName);
        if(state == null){
            state =  new State(request.getState().toLowerCase().trim());
            stateRepository.save(state);
        }


        city =  cityRepository.getByName(cityName);
        if (city == null){
            city  = new City(request.getCity().toLowerCase().trim());
            cityRepository.save(city);
        }


        Address address =  new Address(request.getStreetAddress(), user, "home", country, state, city);
        addressRepository.save(address);


        PhoneContact phoneContact1 =  initializePhoneContact(request.getMobileNumber(),user, true);
        phoneContactRepository.save(phoneContact1);

        if(request.getOfficeNumber() != null){

            PhoneContact phoneContact2 =  initializePhoneContact(request.getOfficeNumber(), user,false);
            phoneContactRepository.save(phoneContact2);
        }


        UserSignUpResponseDto userSignUpResponseDto =  modelMapper.map(user, UserSignUpResponseDto.class);
        apiResponse =  new ApiResponse("Created",HttpStatus.OK, "User Was Created", userSignUpResponseDto);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


    private PhoneContact initializePhoneContact(String phoneNumber, Employee user, boolean isMainNumber){

        PhoneContact phoneContact =  new PhoneContact();
        phoneContact.setPhoneNumber(phoneNumber);
        phoneContact.setIsMainNumber(isMainNumber);
        phoneContact.setEmployee(user);

        return phoneContact;
    }



}
