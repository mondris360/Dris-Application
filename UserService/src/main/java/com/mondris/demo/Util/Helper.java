package com.mondris.demo.Util;

import com.mondris.demo.Util.Api.Exception.CustomErrorClass.IllegalArgumentException;
import org.springframework.stereotype.Service;

@Service
public class Helper {

  public void isPositiveNumber(long number, String currentPath){

      if (number < 1){

          throw  new IllegalArgumentException("Id must be an Integer Number greater than zero", currentPath);
      }
  }

}
