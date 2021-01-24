package com.etranzact.dris.authservice.dris.authservice.Util.SpringSecurity;

import com.etranzact.dris.authservice.dris.authservice.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class MyUserDetails implements UserDetails {
     private String email;
     private String password;
     private boolean isEnabled;
     private List<GrantedAuthority> authorities;

     public MyUserDetails(User user) {
         email =  user.getEmail();
         password = user.getPassword();
         isEnabled = user.getEnabled();
         authorities = user.getAuthorities().stream()
                 .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                 .collect(Collectors.toList());
     }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return   authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
