package com.etranzact.dris.authservice.dris.authservice.Util.SpringSecurity;

import com.etranzact.dris.authservice.dris.authservice.Model.User;
import com.etranzact.dris.authservice.dris.authservice.Repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Primary
public class MyUserDetailsService  implements UserDetailsService {
   @Resource
   private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.getByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Invalid Email Address");
        }

        return new MyUserDetails(user);
    }


}
