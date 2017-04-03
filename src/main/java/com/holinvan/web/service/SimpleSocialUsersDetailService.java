package com.holinvan.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import com.holinvan.web.model.User;
import com.holinvan.web.model.UserAuthority;

public class SimpleSocialUsersDetailService implements SocialUserDetailsService {
	 
	private HolinvanUserDetailsService customUserDetailsService;
	 
    public SimpleSocialUsersDetailService(HolinvanUserDetailsService userDetailsService) {
        this.customUserDetailsService = userDetailsService;
    }
 
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
        User user = (User) customUserDetailsService.loadUserByUsername(userId);
        return (SocialUserDetails) new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getUserAuthorities()), user.getUserData(), null);
    }
    
    private List<GrantedAuthority> getGrantedAuthorities(List<UserAuthority> userAuthorities) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserAuthority userAuthority : userAuthorities) {
            authorities.add(new SimpleGrantedAuthority(userAuthority.getAuthority().getName()));
        }
        return authorities;
    }
 
}