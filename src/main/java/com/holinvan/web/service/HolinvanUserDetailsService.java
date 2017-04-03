package com.holinvan.web.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.holinvan.web.model.User;
import com.holinvan.web.model.UserAuthority;
import com.holinvan.web.repository.UserRepository;

@Service
public class HolinvanUserDetailsService implements UserDetailsService{
	
	private static Logger logger = LoggerFactory.getLogger(UserDetailsService.class);
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null){
        	if (!user.isEnabled()) {
        		throw new DisabledException("User '" + user + "' is disabled");
        	}
        	else {
        		logger.debug("LOGIN : " + user.getUsername());
                return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getUserAuthorities()), user.getUserData(), user.getUserFacturationData());
        	}
        	
        }
        throw new UsernameNotFoundException("USER NOT FOUND");
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(List<UserAuthority> userAuthorities) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserAuthority userAuthority : userAuthorities) {
            authorities.add(new SimpleGrantedAuthority(userAuthority.getAuthority().getName()));
        }
        return authorities;
    }

}
