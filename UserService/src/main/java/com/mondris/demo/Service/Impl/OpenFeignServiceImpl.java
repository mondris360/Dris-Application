package com.mondris.demo.Service.Impl;

import com.mondris.demo.Service.OpenFeignService.OpenFeignUserService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(name="userService", url = "http://localhost:8080/api/v1/")
public class OpenFeignServiceImpl  implements OpenFeignUserService {

}
