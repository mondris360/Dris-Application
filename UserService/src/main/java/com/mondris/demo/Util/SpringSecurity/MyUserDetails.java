//package com.mondris.demo.Util.SpringSecurity;
//
//import com.mondris.demo.Model.Employee;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//public class MyUserDetails implements UserDetails {
//
//     private List<GrantedAuthority>  grantedAuthorities;
//     private String email;
//     private boolean isEnabled;
//
//    public MyUserDetails(Employee employee) {
//        this.grantedAuthorities = employee.get;
//        this.email = email;
//        this.isEnabled = isEnabled;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
